# radley bank

# Description
Task set was to create an application to store and interact with bank accounts and be able to 
interact with it including actions such as create or delete an account and deposit adn withdraw
funds from the account.

# Requirements
Application should contain 2 types of accounts: standard & limited
You should be able to create one type of each account with an initial balance.
You should be able to attempt to deposit and withdraw money from each account.

Requirements for Standard Account:
- Allows the user to deposit money into the account.
- Allows the user to withdraw money from the account if the funds are available.
- If no funds are available then no money can be withdrawn.
- Includes an overdraft of £100.
- Reports current balance of the account which includes any overdraft.

Requirements for Limited Account:
- Has all of the features of a Standard bank account.
- Limits deposits to £50 per deposit.
- Limits withdrawals to £100 per withdrawal.

# Usage

The application is packaged as a JAR and to simplify execution an alias can be added to the environment:

```
alias rb="java -jar out/artifacts/radley_jar/radley.jar"
```

This allows the application to be executed from a shell

```
 % rb
Usage: rb [COMMAND]
Commands:
  create    Create a new account.
  delete    Delete Account
  list      List Accounts
  deposit   Deposit Funds
  withdraw  Withdraw Funds
  report    Account Balance Report
```

## Create an account
```
% rb create -f firstname -s surname -b 1000
First Name: firstname, Last Name: surname, Balance: 1000.000000, Limited: false
New standard account created: 42731
```


## Delete an account
```
 % rb delete -a 42731
Account: 42731 
Account 42731 deleted.

```

## List accounts
```
% rb list
List: 
Accounts: 
Account Name: Sean McDonald Account Number: 46949 Limited: false Balance: 34624.0

Account Name: Stephanie Murray Account Number: 44759 Limited: true Balance: 39694.45

Account Name: Ross Byrne Account Number: 42157 Limited: false Balance: -60.0

Account Name: Emily Carson Account Number: 46955 Limited: true Balance: 29583.56

Account Name: firstname surname Account Number: 51652 Limited: false Balance: 1000.0
```

## Deposit funds

```
 % rb deposit  -a 51652 -v 200
Funds deposited: 200.00
Account 51652 deposit 200.00 succeeded

```

## Withdraw funds
 ```
 % rb withdraw  -a 51652 -v 100 
Funds withdrawn: 100.0
```

## Report balance of an account
````
% rb report  -a 51652 
Account Number: 51652
Current balance: 1100.000000 Overdraft Limit: 100.00
````