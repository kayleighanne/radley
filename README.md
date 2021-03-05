# radley

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

Start the application from the command line:

radley

Create an account
rb create

  The application should prompt the user for the required data and explain what which is.
  
  rb create -b 200 -f Example -s Name


Delete an account
rb delete

  The application should prompt the user for the required data and explain what which is.
  
  rb delete -a 12345


List accounts
rb list


Deposit funds
rb deposit
 
  The application should prompt the user for the required data and explain what which is.
  
  rb deposit -a 12345 -v 100.00


 Withdraw funds
 rb withdraw
 
  The application should prompt the user for the required data and explain what which is.
  
  rb withdraw -a 12345 -v 100.00


Report balance of an account
rb report

  The application should prompt the user for the required data and explain what which is.
  
  rb report -a 12345
