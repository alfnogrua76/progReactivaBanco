package co.com.bancolombia.usecase.registeraccount;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RegisterAccountUseCase {

    private final AccountRepository accountRepository;

    public Mono<Account> register(String name){
        Account acount = Account.builder()
                .id(99L)
                .name(name)
                .build();

        return legalValidation(acount)
                .flatMap(text -> externalService(acount))
                .flatMap(text -> accountRepository.createAccount(acount));

        //return Mono.just(acount);
    }

    private Mono<String> legalValidation(Account account){
        return Mono.just("Ok");
    }

    private Mono<String> externalService(Account account){
        return Mono.just("Service Ok");
    }
}
