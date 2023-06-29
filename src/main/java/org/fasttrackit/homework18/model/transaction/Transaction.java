package org.fasttrackit.homework18.model.transaction;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class Transaction {

    //id
    //product
    //type: SELL/Buy ( daca pot fi de doua feluri atunci facem ENUM)
    //amount double
    private final long id;
    private final String product;
    private final TransactionType type;
    private final double amount;
}
