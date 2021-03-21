---
date: 2021-03-21
tags:
- project
- personalfinance
- ledger
- ledger cli
- budgeting
title: Budgeting with ledger-cli
---
In my
[Personal Finance Philosophy](/personal-finance-philosophy) post, I describe the importance of budgeting and acknowledge that it can be time consuming to do properly. Since I'm a software engineer by trade, I looked for software to help me with the job. Initially, that was practicing double-entry accounting for all my personal finances using [GnuCash](/personal-finance-with-gnucash), an open-source version of Quicken (with less features). It allowed me to understand the workflows, but the integrated expense matcher is terrible and I wanted something better.

<!--excerpt_end-->

I found the [`ledger-cli`](https://www.ledger-cli.org/) tool which is double-entry accounting software that uses a simple text file as your database. This allows you to use version control ([`git`](https://git-scm.com/)) to maintain/version it and write simple scripts to update and modify it (in addition to the already powerful [CLI](https://www.ledger-cli.org/docs.html)).

My new [workflow](/jcf/index.html) for managing my budget looks like this:

1. Monthly, [download CSV exports](#download-csvs) of transactions from my credit card and bank accounts.
1. Use [JCF](https://www.github.com/brentwalther/jcf) to [match the transactions then convert them](#match-and-convert) to a `ledger-cli` compatible file.
1. [Merge each ledger file](#merge-step) into my master ledger.
1. [Check diffs using Git](#maintenance-with-git) and commit the results if everything looks good.
1. [Generate an expenses CSV](#budget-with-pivot-table) and import it into a Google Sheet where I use a pivot table to break down the expenses in to budget categories.

Sound interesting? [Set it up](/jcf/setup.html) for yourself.

## Download transaction CSVs {#download-csvs}

Monthly, I log in to my bank and credit card accounts and download all the transactions that occurred since the last time I balanced the account with my ledger. It could be automated even more using something like [Plaid](https://plaid.com/), but I'm a bit security paranoid so I still do it myself. To start, I invoke ledger to find out the date of the last transaction I have recorded for the account:

```bash
# "Account Name" here means plain substring matching from account names
# in your master ledger. It could be "Assets" to get all assets or "1234"
# to get transactions for Assets:Current Assets:Checking 1234

~/Development/ledger-scripts$ bash register.sh "Account Name"

... prints most recent transactions
```

I download CSVs for all transactions that aren't already in the register (based off date). If I'm balancing a brand new account I just start with 30/60 days. Next, I [match them using JCF](/jcf/matching.html) to expense accounts.

## Match transactions and convert to ledger format {#match-and-convert}

See JCF documentation page for [Matching](/jcf/matching.html)

## Merge the new ledgers {#merge-step}

See JCF documentation page for [Merging](/jcf/merging.html)

## Use git to maintain my databases {#maintenance-with-git}

See JCF documentation page for [Committing an update](/jcf/commit.html)

## Use a spreadsheet with pivot table {#budget-with-pivot-table}

See JCF documentation page for [Expense Reports](/jcf/expense_report.html)
