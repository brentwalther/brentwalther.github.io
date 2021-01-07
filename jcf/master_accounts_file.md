---
title: Master accounts file
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "master", "accounts", "file"]
layout: jcf-documentation
date: 2021-01-06
---

The JCF tool uses the accounts listed in your `master.ledger` and `master.accounts` files as a basis for user input and match guesses. Before matching a CSV, consider whether all of the accounts listed in your `master.accounts` are sufficient. You likely have plenty from the example `master.accounts` file you [originally set up](/jcf/setup.html#create-the-repo), but you can add as many as you'd like.

One primary consideration is reporting. If you'd like to create a budget report you should make sure all categories you want tracked in that budget have their own account.

### Examples

- `account Assets:Current Assets:Checking 1234`
- `account Assets:Current Assets:Investments:Vanguard`
- `account Expenses:Category:Sub Category`
- `account Expenses:Fun Budget:Concerts`
- `account Expenses:Bills:Insurance:Vehicle`
- `account Income:Salary`
- `account Income:Dividends`
- `account Income:Capital Gains:Unrealized`
- `account Liabilities:Credit Card:Chase`
- `account Liabilities:Loans:Student Loan`

### What is: `Equity:Opening Balances`
If you aren't familiar with accounting I thought I would mention what this account is for because it confused me when I first started. I had heard the term 'equity' when referring to equity in a home, but never in another accounting context. The purpose of this account is solely to record any money you already had that initially "appeared" in the account as the opening balance.