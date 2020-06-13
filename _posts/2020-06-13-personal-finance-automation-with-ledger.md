---
title: Budgeting with ledger-cli
tags: ["project", "personalfinance"]
---

In my
[Personal Finance Philosophy](/personal-finance-philosophy) post, I talk about the important of budgeting but also how it's time consuming to do properly. I knew this is something software could help with so when I initially began doing double-entry accounting for all my personal finances I used
[GnuCash](/personal-finance-with-gnucash), an open-source accounting tool. It worked well but there was still a lot of manual process and the expense matcher is terrible to use. I wanted something better.

I found the [`ledger-cli`](https://www.ledger-cli.org/) tool which is double-entry accounting software where your ledger is a simple text file instead of something sophisticated like a database. This allows me to use version control to maintain it and write simple scripts to update and modify it (in addition to its already powerful CLI).

My new workflow for managing my budget looks like this:

1. Every month, [download CSV exports](#download-csvs) of transactions from my credit card and bank accounts.
2. Use my JCF tool to automatically [match the transactions and export them](#match-and-convert) to a ledger-cli compatible file.
3. [Merge each ledger file](#merge-and-ingest) into the main ledger.
4. Use ledger-cli to [export an updated master CSV and mapping file](#export-csv-and-mappings) of all expenses.
5. [Check diffs using Git](#maintenance-with-git) and commit the results if they look correct.
6. [Import the CSV](#budget-with-pivot-table) into a spreadsheet and use a pivot table to break down expenses in to budget categories.

If you want to skip the details, there are [scripts](#scripts) and [example files](#example-files) at the bottom.

## Download transaction CSVs {#download-csvs}

Each month, I log in to my bank and credit card accounts and download all the transactions that occurred in the last month. I know that I could automate this even more using something like [Plaid](https://plaid.com/), but I'm a bit security paranoid so I still do it myself. I run my [balances](#check-balances) script to find the beginning date for each of the exports, and use the end date of the end of last month. I collect all these in a folder.

## Match transactions and convert to ledger format {#match-and-convert}

Once I have all my CSV files with transactions from the accounts, I run them through my [JCF](https://www.github.com/brentwalther/jcf) tool to match them and then export them to a standalone ledger file. I do this for each CSV one at a time and export each to their own ledger file. The JCF tool requires that you inspect the CSV first and specify the date format and the column ordering. Here's an example invocation of the tool:

```bash
bazel run :csv_matcher -- \
  --mapping_file '/mnt/nas/Finance/ledger/payee-account-mappings.tsv' \
  --transaction_csv ~/Downloads/transactions\ \(5\).csv \
  --csv_field_ordering 'date,,amt,,desc' \
  --date_format 'yyyy-MM-dd' \
  --output ~/Downloads/brent_chk_may.ledger
```

## Merge and ingest the new ledgers {#merge-and-ingest}

After converting all the CSV files, I'll have a collection of individual ledger files to merge in to the master ledger. I merge them one at a time using my [ingest.sh](#ingest) script, specifying the name of the account when I invoke it. Here's an example:

```bash
bash ingest.sh ~/Downloads/brent_chk_may.ledger "Assets:Current Assets:Brent Checking"
```

## Export the new master CSV and mapping files {#export-csv-and-mappings}

After ingesting a single account, I use my [`export-expense-csv.sh`](#export-expense-csv) and [`export-expense-mappings.sh`](#export-expense-mappings) scripts to update the master expense CSV and expense mapping files that live right beside my master ledger. I use git to maintain these files too.

## Use git to maintain my databases {#maintenance-with-git}

Next, I can use `git diff` to compare the new master ledger, CSV, and mapping files to the new ones (with newly merged transactions). If everything checks out, I `git add` them and `git commit` the updates. I do this after every single merge which ensures I never waste any work if I need to revert back to a previous state.

## Use a spreadsheet with pivot table {#budget-with-pivot-table}

Finally, I use a [pivot table](https://support.google.com/docs/answer/1272900) in Google Sheets to create a budget table that breaks down all the expense categories by month. The master expense CSV file can be imported to its own special sheet in one go (and replaced atomically) and the pivot table lives in a separate sheet.

## Scripts {#scripts}

### Variables

I keep paths to all the 'global' variables in a `vars.sh` file to make other scripts easier to produce. I build ledger from source but you can probably download a prebuilt binary too.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=vars.sh"></script>

### Check Balances {#check-balances}

I use this primarily to quickly see what the last recorded transaction is for each of my credit card and bank accounts to know the beginning date for CSV exports on their websites.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=balances.sh"></script>

### Ingest a converted ledger file {#ingest}

Right now, my [JCF](https://www.github.com/brentwalther/jcf) tool exports matched transactions to a ledger file with splits between the matched account and "An Account". I use this script to fill in the correct name, concatenate it with the master ledger, use --pedantic to make sure I'm not importing some rogue account, and print out the new master ledger in a nice sorted format. Afterwards, I can use a diff-tool to compare the updated file.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=ingest.sh"></script>

### Export expense CSV {#export-expense-csv}

This is used to update the master CSV file I use in my budget spreadsheet. This creates a massive file that can be easily sorted/grouped by a pivot table.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=export-expense-csv.sh"></script>

### Export expense mappings {#export-expense-mappings}

This exports a large tab-separated-value (TSV) file as an input for the matcher built in to my [JCF](https://www.github.com/brentwalther/jcf) tool. It's just a giant list descriptions and respective expense account separated by a tab.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=export-expense-mappings.sh"></script>

### Reformat {#reformat}

Sometimes I want to make some local edits in the ledger. In order to not worry about formatting, I just make the edits then run this over the ledger to reformat and sort it.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=reformat.sh"></script>

## Example files {#example-files}

### `master.ledger`

Here's a small excerpt of my actual master ledger:

```
2020/05/27 * HEB FOOD STORES
    Liabilities:Credit Card:American Express Blue Cash Preferred                $-6.11
    Expenses:Groceries

2020/05/28 * TST* LAZARUS BREWING CO.
    Expenses:Food/Drink:Alcohol & Bars                                          $44.31
    Liabilities:Credit Card:Chase

2020/05/28 * AMZN Mktp US*M70CA3W41
    Expenses:Shopping:Amazon/Online                                              $6.48
    Liabilities:Credit Card:Capital One Venture

2020/05/28 * TARGET.COM  *
    Expenses:Shopping:Clothing                                                  $97.43
    Liabilities:Credit Card:Capital One Venture

2020/05/28 * SPOTIFY MUSIC SUBS USD RECURRING
    Liabilities:Credit Card:American Express Blue Cash Preferred               $-16.23
    Expenses:Bills:Streaming

2020/05/29 * Dividends Reinvested
    Assets:Investments:Personal:VWSTX                                           $13.48
    Income:Unrealized Capital Gains:Dividends (Tax Free)

2020/05/29 * Dividends Reinvested
    Assets:Investments:Personal:VWITX                                            $8.13
    Income:Unrealized Capital Gains:Dividends (Tax Free)

2020/05/29 * Dividends Reinvested
    Assets:Investments:Personal:VMMXX                                            $1.05
    Income:Unrealized Capital Gains:Dividends

2020/05/29 * MATTS EL R* MATTS EL R
    Expenses:Food/Drink:Restaurants                                             $53.92
    Liabilities:Credit Card:Chase

2020/05/29 * Kindle Svcs*M78CA4DA2
    Expenses:Shopping:Books                                                     $12.98
    Liabilities:Credit Card:Capital One Venture
```

### `master.accounts`

My master accounts file is just a list of all accounts in the ledger precedded with 'account'. `ledger-cli` supports some fancy virtual account and commodity stuff but I don't use any of it at the moment. To get an idea of what accounts I use, see the list [here](/personal-finance-with-gnucash#setup).

### `expenses.csv`

Here's an example of the format of my master CSV file that's generated using the [`export-expense-csv.sh`](#export-expense-csv) script above:

```
"2020/04/03","Expenses:Groceries","78.65"
"2020/04/02","Expenses:Shopping:Amazon/Online","10.81"
"2020/04/01","Expenses:Bills:RentalInsurance","14.07"
"2020/04/01","Expenses:Food/Drink:Coffee Shops","27"
"2020/04/01","Expenses:Shopping:Amazon/Online","14.06"
"2020/04/01","Expenses:Food/Drink:Convenient Store","7.99"
"2020/03/31","Expenses:Food/Drink:Alcohol & Bars","40.57"
"2020/03/30","Expenses:Shopping:Amazon/Online","17.17"
"2020/03/30","Expenses:Food/Drink:Convenient Store","6.47"
"2020/03/30","Expenses:Food/Drink:Restaurants","43.96"
"2020/03/30","Expenses:Food/Drink:Restaurants","8"
"2020/03/29","Expenses:Food/Drink:Fast Food","23.14"
"2020/03/29","Expenses:Groceries","157.24"
"2020/03/28","Expenses:Auto:Gas","50.08"
```

### `payee-account-mappings.tsv`

This is the input to my [JCF](https://www.github.com/brentwalther/jcf) tool and generated by the [`export-expense-mappings.sh`](#export-expense-mappings) script above. It's simply the transaction description followed by a tab then the expense account it went to:

```
HOMEGOODS #503  Expenses:Shopping:Home
City of Austin T PAYMENT~ Future Amount: 29.15 ~ Tran: ACHDW    Expenses:Bills:Electric
Amazon.com*L37GD4ET3    Expenses:Shopping:Amazon/Online
GOOGLE*GOOGLE FI        Expenses:Bills:Mobile Phone
WALGREENS #1933 Expenses:Food/Drink:Convenient Store
TORCHYS TACOS NORTHSHORE        Expenses:Food/Drink:Restaurants
CORNER STORE 0967 00AUSTIN              TX      Expenses:Food/Drink:Convenient Store
CORNER STORE 0967 00AUSTIN              TX      Expenses:Auto:Gas
LEIF JOHNSON BEN WHITE  Expenses:Auto:Repair and Maintenance
AMZN Mktp US*7T6F82WE3  Expenses:Shopping:Amazon/Online
TST* PINTHOUSE PIZZA - SO       Expenses:Food/Drink:Restaurants
MARKET@WORK 2067379149  Expenses:Food/Drink:Fast Food
CHUY'S  Expenses:Food/Drink:Restaurants
```