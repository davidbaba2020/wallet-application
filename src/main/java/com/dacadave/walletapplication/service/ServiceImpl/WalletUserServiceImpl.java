package com.dacadave.walletapplication.service.ServiceImpl;

import com.dacadave.walletapplication.dto.AccountActivationDto;
import com.dacadave.walletapplication.dto.PinChangeDto;
import com.dacadave.walletapplication.dto.WalletUserDto;
import com.dacadave.walletapplication.entities.Wallet;
import com.dacadave.walletapplication.entities.WalletUser;
import com.dacadave.walletapplication.enums.WalletType;
import com.dacadave.walletapplication.exceptions.AccountIsNotActivatedException;
import com.dacadave.walletapplication.exceptions.PinMustBeDifferentFromInitialPinException;
import com.dacadave.walletapplication.exceptions.UserAlreadyExistsException;
import com.dacadave.walletapplication.exceptions.UserWithEmailNotFound;
import com.dacadave.walletapplication.repository.WalletRepository;
import com.dacadave.walletapplication.repository.WalletUserRepository;
import com.dacadave.walletapplication.service.WalletUserInterface;
import com.dacadave.walletapplication.utils.mail_sender.MailSenderImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static com.dacadave.walletapplication.global_constants.Constants.*;

@Service
@RequiredArgsConstructor
public class WalletUserServiceImpl implements WalletUserInterface
{

    private final WalletUserRepository walletUserRepository;
    private final WalletRepository walletRepository;
    private final ModelMapper mapper;
    private final MailSenderImpl mailSender;

    @Override
    public String signUpAsAUser(WalletUserDto walletUser)
    {
        Optional<WalletUser> userDoExist = walletUserRepository.findUserByEmail(walletUser.getEmail());

        if(userDoExist.isEmpty())
        {
            WalletUser newUser = mapper.map(walletUser, WalletUser.class);
            walletUserRepository.save(newUser);

            Wallet newWallet = walletRepository.save(createWalletForNewUser(newUser, this.walletRepository));

            String messageToActivate = (
                        "Welcome "+ newUser.getFirstName() +" " + newUser.getLastName()+". Your Wallet has been created successfully," +
                         " to activate, use four digits sent to your mail and set a new transaction pin to commence any form of transaction.:"
                        + newWallet.getWalletAccountNumber().substring(6,newWallet.getWalletAccountNumber().length())
                    );

            mailSender.sendMail(
                    "DAVACOM_WALLET_INT_BIZ",
                    walletUser.getEmail(),
                    "Account creation And Activation Process",
                    messageToActivate
            );

        } else
        {
            throw new UserAlreadyExistsException("A user with this email already exist!");
        }
        return null;
    }

    @Override
    public String accountActivation(AccountActivationDto accountActivation)
    {
        String msg = "";
        Optional<WalletUser> userDoExist = Optional.of(walletUserRepository.findUserByEmail(accountActivation.getEmail())).orElseThrow(()->
                new UserWithEmailNotFound(USER_NOT_FOUND));

        Optional<Wallet> usersWalletExist = Optional.of(walletRepository.findByAccountHolderEmail(accountActivation.getEmail())).orElseThrow(()->
                new UserWithEmailNotFound(WALLET_NOT_FOUND));

        if(userDoExist.get().isAccountVerified())
        {
            msg = "Your account is already Activated";
        }
        else if(Objects.equals(accountActivation.getCode(), usersWalletExist.get().getTransactionPin()))
        {
            userDoExist.get().setAccountVerified(true);
            walletUserRepository.save(userDoExist.get());
            msg = "Your Account has been successfully activated";
        }

        return msg;
    }

    @Override
    public String changeTransactionPin(PinChangeDto pinChangeDto)
    {
        Optional<WalletUser> userDoExist = Optional.of(walletUserRepository.findUserByEmail(pinChangeDto.getWalletOwnerEmail())).orElseThrow(()->
                new UserWithEmailNotFound(USER_NOT_FOUND));

        Optional<Wallet> usersWalletExist = Optional.of(walletRepository.findByAccountHolderEmail(userDoExist.get().getEmail())).orElseThrow(()->
                new UserWithEmailNotFound(WALLET_NOT_FOUND));

        if(userDoExist.get().isAccountVerified()) {
            if(!Objects.equals(pinChangeDto.getNewPin(), usersWalletExist.get().getTransactionPin()))
            {
                usersWalletExist.get().setTransactionPin(pinChangeDto.getNewPin());
                walletRepository.save(usersWalletExist.get());
                return "Pin successfully changed.";
            }
            else
            {
                throw new PinMustBeDifferentFromInitialPinException(PIN_MUST_BE_DIFFERENT);
            }
        }
        else
        {
            throw new AccountIsNotActivatedException(ACCOUNT_NOT_ACTIVATED);
        }
    }




    static Wallet createWalletForNewUser(WalletUser newUser, WalletRepository walletRepository)
    {
        String generatedAccountNumber = generateRandomAccountNumber();

        Optional<Wallet> checkWallet = walletRepository.findWalletByWalletAccountNumber(generatedAccountNumber);
        if(checkWallet.isPresent())
        {
            generatedAccountNumber = generateRandomAccountNumber();
        }

        return Wallet.builder()
                .accountHolderEmail(newUser.getEmail())
                .walletOwnerId(newUser.getId())
                .walletType(WalletType.BRONZE)
                .walletAccountNumber(generatedAccountNumber)
                .currentBalance(0.00)
                .transactionPin(generatedAccountNumber.substring(6,generatedAccountNumber.length()))
                .build();
    }

    static String generateRandomAccountNumber ()
    {
        StringBuilder accNum = new StringBuilder();
        Random rand = new Random();
        for (int i = 1; i <= 5; i++) {
            int resRandom = rand.nextInt((99 - 10) + 1) + 10;
            accNum.append(resRandom);
        }
        return accNum.toString();
    }

}
