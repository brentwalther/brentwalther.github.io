---
title: Budgeting with ledger-cli
tags: ["project", "personalfinance"]
---

In my
[Personal Finance Philosophy](/personal-finance-philosophy) post, I describe the importance of budgeting and acknowledge that it can be time consuming to do properly. Since I'm a software engineer by trade, I looked for software to help me with the job. I initially began doing double-entry accounting for all my personal finances I used
[GnuCash](/personal-finance-with-gnucash), an open-source accounting tool. It allowed me to understand the workflows, but the integrated expense matcher is terrible and I wanted something better.

I found the [`ledger-cli`](https://www.ledger-cli.org/) tool which is double-entry accounting software that uses a simple text file as your database. This allows you to use version control (git) to maintain/version it and write simple scripts to update and modify it (in addition to the already powerful CLI).

My new workflow for managing my budget looks like this:

1. Every month, [download CSV exports](#download-csvs) of transactions from my credit card and bank accounts.
2. Use my [JCF](https://www.github.com/brentwalther/jcf) tool to automatically [match the transactions and export them](#match-and-convert) to a ledger-cli compatible file.
3. [Merge each ledger file](#merge-step) into the main ledger.
4. Use ledger-cli to [export an updated master CSV and mapping file](#export-csv-and-mappings) of all expenses.
5. [Check diffs using Git](#maintenance-with-git) and commit the results if they look correct.
6. [Import the master expenses CSV](#budget-with-pivot-table) into a spreadsheet and use a pivot table to break down the expenses in to budget categories.

If you want to skip the details, there are [scripts](#scripts) and [example files](#example-files) at the bottom.

## Setup

To try and follow follow this workflow, you'll need to get some of the software set up:

1. To run [JCF](https://www.github.com/brentwalther/jcf), you need to install the build system [`bazel`](https://docs.bazel.build/versions/3.3.0/getting-started.html).
2. Use git to clone [JCF](https://www.github.com/brentwalther/jcf).
3. Either download a precompiled binary or build `ledger-cli` from source: [https://www.ledger-cli.org/download.html](https://www.ledger-cli.org/download.html)
4. Download the [scripts](#scripts) the edit the [variables.sh](#variables) file with paths to your ledger files/executable.
5. Create an initial master.accounts file. For an example list of accounts, see [here](/personal-finance-with-gnucash#setup).

## Download transaction CSVs {#download-csvs}

Each month, I log in to my bank and credit card accounts and download all the transactions that occurred in the last month. I know that I could automate this even more using something like [Plaid](https://plaid.com/), but I'm a bit security paranoid so I still do it myself. If I can't remember where I left off in any given account, I can invoke ledger to find out:

```bash
~/Development/ledger/ledger -f /path/to/master.ledger register "Account:Name" --tail 10 --sort date
```

After downloading each one, I use the linux `scp` program to copy them from my laptop to my server.

## Match transactions and convert to ledger format {#match-and-convert}

Once I have all my transaction CSV files, I transform each one to a matched ledger-compatible file using my [JCF](https://www.github.com/brentwalther/jcf) tool. The tool handles determining CSV column ordering, date format, and does account guessing or tab-completed manual matching. Here's an example invocation of the tool:

```bash
cd ~/Development/jcf

bash match.sh /home/brentwalther/Downloads/1234_jul "Assets:Account 1234" /path/to/payee-account-mappings.tsv /path/to/master.accounts
```

You'll need to have the master accounts file already set up (it powers the tab-completion in the matcher). If it's your first time matching transactions, you won't have any mappings yet (which requires you to use the tab-completed matching to start), but you should create an empty file near your master ledger. You'll update the mappings file after every single CSV import.

## Merge the new ledgers {#merge-step}

After converting all the CSV files, I'll have a collection of individual ledger files to merge in to the master ledger. I merge them one at a time using my [merge.sh](#merge) script, specifying the name of the account when I invoke it. Here's an example:

```bash
bash merge.sh ~/Downloads/1234_jul.ledger
```

## Export the new master CSV and mapping files {#export-csv-and-mappings}

After merging an account, I use my [`export-expense-csv.sh`](#export-expense-csv) and [`export-expense-mappings.sh`](#export-expense-mappings) scripts to update the master expense CSV and expense mapping files that live right beside my master ledger. I use git to maintain all these files.

## Use git to maintain my databases {#maintenance-with-git}

Next, I can use `git diff` to compare the new master ledger, CSV, and mapping files to the new ones (with newly merged transactions). If everything checks out, I `git add` them and `git commit` the updates. I do this after every single merge which ensures I never waste any work if I need to revert back to a previous state.

## Use a spreadsheet with pivot table {#budget-with-pivot-table}

Finally, I use a [pivot table](https://support.google.com/docs/answer/1272900) in Google Sheets to create a budget table that breaks down all the expense categories by month. The master expense CSV file can be imported to its own special sheet in one go (and replaced atomically) and the pivot table lives in a separate sheet.

## Scripts {#scripts}

### Variables {#variables}

I keep paths to all the 'global' variables in a `vars.sh` file to make other scripts easier to produce. I build ledger from source but you can probably download a prebuilt binary too.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=vars.sh"></script>

### Check Balances {#check-balances}

I use this primarily to quickly see what the last recorded transaction is for each of my credit card and bank accounts to know the beginning date for CSV exports on their websites.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=balances.sh"></script>

### Merge a converted ledger file {#merge}

The [JCF](https://www.github.com/brentwalther/jcf) tool is designed to match all transactions from a CSV and exports a ledger compatible file, but you may need to make sure you aren't imbalanced. When using the `merge.sh`, it'll use the `master.accounts` file and `--pedantic` CLI option to make sure there are no rogue accounts and then it sorts and formats the whole master ledger. Afterwards, I can use any diff-tool to compare the updated file.

<script src="https://gist.github.com/brentwalther/c9dcfbb7d32604566aab91d6a65744ed.js?file=merge.sh"></script>

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

My master accounts file is just a list of all accounts in the ledger preceeded with `account ` (e.g. `account Assets:Bank:Ally Checking`). `ledger-cli` supports some fancy virtual account and commodity stuff but I don't use any of it at the moment.

I've got an example list of accounts [here](/personal-finance-with-gnucash#setup).

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
City of Austin   Expenses:Bills:Electric
Amazon.com    Expenses:Shopping:Amazon/Online
GOOGLE*GOOGLE FI        Expenses:Bills:Mobile Phone
WALGREENS Expenses:Food/Drink:Convenient Store
TORCHYS TACOS NORTHSHORE        Expenses:Food/Drink:Restaurants
CORNER STORE              TX      Expenses:Food/Drink:Convenient Store
CORNER STORE              TX      Expenses:Auto:Gas
LEIF JOHNSON BEN WHITE  Expenses:Auto:Repair and Maintenance
AMZN Mktp  Expenses:Shopping:Amazon/Online
TST* PINTHOUSE PIZZA - SO       Expenses:Food/Drink:Restaurants
MARKET@WORK  Expenses:Food/Drink:Fast Food
CHUY'S  Expenses:Food/Drink:Restaurants
```