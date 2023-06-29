package org.fasttrackit.homework18.controller.transaction;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.homework18.model.transaction.Transaction;
import org.fasttrackit.homework18.model.transaction.TransactionType;
import org.fasttrackit.homework18.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    //GET / transaction - get all transactions. Make it filterable by product, type, minAmount, maxAmount
    @GetMapping // GET http://host:port/transactions?product=bread&type=SELL&minAmount=0.5&maxAmount=3

    public List<Transaction> getAll(@RequestParam(required = false) String product,
                                    @RequestParam(required = false) TransactionType type,
                                    @RequestParam(required = false) Double minAmount,
                                    @RequestParam(required = false) Double maxAmount) {
       return transactionService.getAll(product, type, minAmount, maxAmount);
    }
    //GET / transaction/{id} - get transactions with id
    @GetMapping("/{id}")
     public Transaction getById(@PathVariable long id){
        return transactionService.getById(id);
     }
    // POST / transactions - adds a new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody CreateTransactionRequest transaction){
        return transactionService.add(transaction.amount(),transaction.product(),transaction.type());
    }
    // PUT / transactions/ {id} - replaces the transaction with id
    //
    //DELETE / transactions/{id} - deletes the transaction with id
    //
    //
    // GET/transaction/reports/type -> returns a map from type to list of transactions of that type
    @GetMapping("/reports/type")
    public ReportByTypeResponse generateReportByType(){
        Map<TransactionType, List<Transaction>> reportyByType = transactionService.getReportyByType();
        return new ReportByTypeResponse(reportyByType.get(TransactionType.SELL), reportyByType.get(TransactionType.BUY));
    }
    //GET /transactions/reports/product -> returns a map from product to list of transactions for that product
}
