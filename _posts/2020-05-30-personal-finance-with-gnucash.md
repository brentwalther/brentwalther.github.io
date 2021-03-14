---
tags:
- personalfinance
title: Personal Finance with GnuCash
---
- [Background](#background)
- [What is _double entry accounting_?](#what-is-double-entry-accounting)
- [GnuCash](#gnucash)
  - [Setup](#setup)
  - [Updating the ledger](#updating)
  - [Reports](#reports)
- [Getting tired](#tired)

## Background {#background}

Shortly after I graduated from college and began my career, I became more
interested in personal finance and wanted to be more hands-on with the
management of all of my finances (more thoughts can be found in
[Personal Finance Philosophy](/personal-finance-philosophy)). I wanted to be
able to see all of my finances in one place, run reports (budget, cash flow, and
net worth reports mostly), and feel like I knew exactly where money was coming
from and going. I stumbled across
[double entry accounting](https://www.investopedia.com/terms/d/double-entry.asp)
and the free and open-source accounting program
[GnuCash](https://www.gnucash.org/) and knew it would allow me to build the
complete picture that I desired. I just needed to learn how to use it.

## What is _double entry accounting_? {#what-is-double-entry-accounting}

Double entry accounting may have confusing online definitions for somebody who's
never studied it in school, but it essentially boils down to a simple principle:
any money that flows to or from an account you come from or go to some other
account (it's entered twice (double entry) - once in each account). Some simple
examples:

- When your paycheck is deposited in your bank account, where does it come from?
  Answer: an "Income/Salary" account.
- When you make a purchase at a restaurant and the money you charge it to your
  credit card, where does that money go? Answer: an "Expense/Restaurants"
  account.
- When you pay your credit card, what happens? Money is deducted from your bank
  account is added to the credit card account.

There are five types of accounts: Income, Expense, Asset, Liability, and Equity.
For the purposes of personal finance, the first 4 are the most interesting. All
transactions are 'split' between 2 of these accounts (sometimes more, but that's
getting advanced) and recorded in a 'ledger'. The cash 'flows' from one to the
other. Here's a simple example where every dollar amount is a split that's
recorded in the ledger:

<figure>
  <img src="/img/double_entry_accounting_example.png" alt="Simple example image of double-entry accounting.">
  <figcaption>There are 5 transactions in this example.</figcaption>
</figure>

The accompanyin ledger (if you were looking at all accounts at once) would look
like the below table. Note that the balance is the running balance for each
individual account, not a total running balance. I use parantheses to represent
negative balances:

| Date       | Description  | Account                 | Amount | Balance |
| ---------- | ------------ | ----------------------- | ------ | ------- |
| 2020-05-07 | Paycheck     | Income:Salary           | -$1000 | ($1000) |
| 2020-05-07 | Paycheck     | Assets:Checking Account | +$1000 | $1000   |
| 2020-05-14 | Walmart      | Liability:Credit Card   | -$200  | ($200)  |
| 2020-05-14 | Walmart      | Expenses:Groceries      | +$200  | $200    |
| 2020-05-16 | Tex-Mex Food | Liability:Credit Card   | -$100  | ($300)  |
| 2020-05-16 | Tex-Mex Food | Expenses:Restaurants    | +$100  | $100    |
| 2020-05-21 | Paycheck     | Income:Salary           | -$1000 | ($2000) |
| 2020-05-21 | Paycheck     | Assets:Checking Account | +$1000 | $2000   |
| 2020-05-31 | Rent         | Assets:Checking Account | -$1300 | $700    |
| 2020-05-31 | Rent         | Expenses:Rent           | +$1300 | $1300   |
| 2020-05-31 | Pay CC       | Assets:Checking Account | -$300  | $400    |
| 2020-05-31 | Pay CC       | Liability:Credit Card   | +$300  | $0      |

The ledger becomes a simple database. To see if you've fully reconciled the
account and recorded all the transactions, you can simply compare the balance in
your ledger with whatever your actual balance is online. They should be equal if
you've reconciled all transactions.

## GnuCash {#gnucash}

GnuCash is simply an accounting tool that allows you to do double-entry
accounting just like above with accounts that you set up. You can enter
transactions manually (which is tedious but fine if there are few to catch up)
or you can import them using CSV or OFX files (most banks and credit card
companies have "download transactions" buttons for one or both of these
formats). The main screen is an account overview that shows the overall balance
of every acccount and you can dig in to the individual ledger for each account
to look at the transactions for it.

### Setup {#setup}

The biggest hurdle when setting up GnuCash is understanding what accounts you
need to set up. It can seem like a lot to manage at first, but you'll realize
quickly that most of the accounts are the expense categories you want to track
in your budget. When I started, these were the accounts I started with:

- Assets:Current Assets:Ally Checking
- Assets:Current Assets:Ally Savings
- Assets:Current Assets:Cash
- Assets:Investments:Retirement:401K
- Assets:Investments:Retirement:Roth IRA
- Equity:Opening Balances
- Expenses:Auto:Car Wash
- Expenses:Auto:Fees
- Expenses:Auto:Gas
- Expenses:Auto:Parking
- Expenses:Auto:Repair and Maintenance
- Expenses:Auto:Tolls
- Expenses:Bills:Auto Insurance
- Expenses:Bills:Electric
- Expenses:Bills:Gym
- Expenses:Bills:Internet
- Expenses:Bills:Mobile Phone
- Expenses:Bills:Rental Insurance
- Expenses:Bills:Streaming
- Expenses:Entertainment:Music/Movies
- Expenses:Entertainment:Recreation
- Expenses:Food/Drink:Alcohol & Bars
- Expenses:Food/Drink:Coffee Shops
- Expenses:Food/Drink:Restaurants
- Expenses:Groceries
- Expenses:Health:Dentist
- Expenses:Health:Doctor
- Expenses:Miscellaneous
- Expenses:Rent
- Expenses:Shopping:Amazon/Online
- Expenses:Shopping:Clothing
- Expenses:Shopping:Electronics & Software
- Expenses:Shopping:Misc
- Expenses:Taxes
- Expenses:Transportation:Public Transportation
- Expenses:Transportation:Rental Car & Taxi
- Expenses:Travel:Air Travel
- Expenses:Travel:Hotel
- Imbalance-USD
- Income:Interest Income:Checking Interest
- Income:Interest Income:Savings Interest
- Income:Retirement
- Income:Salary
- Liabilities:Credit Card:Capital One
- Liabilities:Credit Card:Chase
- Liabilities:Loans:Student Loan

Note that I only had a few physical accounts:

1. An Ally checking and savings account.
1. A 401k and Roth IRA with Vanguard
1. A credit card with Capital One
1. A credit card with Chase
1. A student loan account

To get started, you essentially need to add all the accounts you want to track
and then balance them all up. To do this in the very beginning, I just used
transferred money to/from the "Equity>Opening Balance" account to all the
physical accounts so that the balances matched what they showed online. This
balancing step where you transfer money from to open the balance is something
you only ever do once when you set everything up. From then on, you only track
transactions that appear in the accounts.

### Updating the ledger {#updating}

Normally I update the ledger once a month. Usually, I'll do it in the first few
days of the month and import/reconcile all the transactions from the previous
month for each account. I'll usually do the following:

1. Log in to Ally and enter the transactions from the month manually. I don't
   normally use my debit card to make any purchases so the number of
   transactions is usually limited to paychecks, interest, rent, and credit card
   payments. The transfers either come from an Income account or go to a
   Liability or Expense account directly.
1. Log on to Vanguard and add any new investment purchases that came from my
   payroll. Since payroll deductions never actually enter your bank account,
   this money transfers from an "Income:Retirement" account. Since investments
   grow, the balance will not always match what you have in GnuCash. After
   practicing this accounting workflow for a hile, I began doing reconcile
   balance transfers from an "Income:Unrealized Capital Gains" account.
1. Log on to each credit card and download all the transactions as a CSV or OFX
   and import them using the GnuCash import tool. Sometimes it matches them to
   expense accounts automatically (though my main complaint with GnuCash is how
   crappy the auto-matcher is). Un-matched expenses get matched to the
   "Imbalance-USD" account automatically and you just need to change the
   transfer account to an Expense account for each transaction.
1. Cross-check all the balances of my physical accounts with what GnuCash says
   to make sure they're up-to-date and I didn't miss any transactions.
1. Done!

### Reports {#reports}

GnuCash has some reasonably rich reports for your accounts. In particular, the
Expenses report can break down all your expenses categories by month and show
you where all your money is going. Usually I would take the data from this
report and put it in a Google Sheets spreadsheet to make more interesting graphs
or format it exactly how I wanted it. I'd also run a Net Worth report and break
down the Asset and Liability categories to see how I was saving. Ideally your
Net Worth should grow over time as you save.

I looked at the Cash Flow reports for a little bit too (but don't regularly
consult them) to see a precises picture of how much of my income was actually
getting spent. Ideally your cash flow is always in the green which means you
spent less than you earned. That green should go into the appropriate savings
bucket per your own personal philosophy and financial position.

## Getting tired of GnuCash {#tired}

While GnuCash is a powerful tool, I eventually got to the point where the
bottleneck of the entire workflow was the crappy transaction matcher. Even with
a couple thousand transactions examples in the database, it still failed to
auto-match many transactions which ended up taking more time than I wanted to
dedicate to it. I eventually switched to plain-text accounting using
[ledger-cli](https://www.ledger-cli.org/) and wrote a simple text based expense
matching tool called [JCF](/jcf). I'm able to get significantly more done in
less time using the workflows.
