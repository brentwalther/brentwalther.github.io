---
title: Matching
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "transactions", "csv", "matcher"]
layout: jcf-documentation
date: 2021-01-06
---

I use the [JCF](https://github.com/brentwalther/jcf) tool to convert my bank's transactions CSV files to `ledger` compatible files.

Never head of JCF? See the [setup](/jcf/setup.html).

JCF is a tool I wrote that can read CSV files exported by bank and credit card company websites, provide text-based prompts to help you match the transactions to other accounts, and then export `ledger` files that can be merged in to a master ledger. Invoking and using the tool is very easy with some quick extra setup:

1. Adding a [settings profile](/jcf/settings_profiles.html) for the institution's CSV format.
2. Ensuring your [`master.accounts`](/jcf/master_accounts_file.html) file has all the accounts you intend to use for matching.

After that, I convert a CSV file to a ledger file like this:

```
cd ~/Development/ledger-scripts
# Make sure you have your JCF workspace set in vars.sh!
bash match.sh ally,1234 ~/Downloads/input.csv ~/Downloads/output.ledger
```

After running the command, JCF will import all your existing account and transactions then present you with a series of match prompts like the following:

```
┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
│4 left to match (including this one).                                                                       │
├────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
│Jan 29, 2020 - Torchy's Tacos                                                                               │
│    -$18.25    Liabilities:Credit Card:Chase                                                                │
│                                                                                                            │
│(1)  Expenses:Food/Drink:Restaurants                                                                        │
│(2)  Expenses:Food/Drink:Alcohol & Bars                                                                     │
│(3)  Leave unmatched.                                                                                       │
└────────────────────────────────────────────────────────────────────────────────────────────────────────────┘
Enter an option number (1 of 3), type a character and hit tab to autocomplete from 52 options, or hit enter
to accept the default (1):
```

The options are calculated for every transaction and are based off the existing transaction examples in your master ledger and improve over time as you get more examples. If you're just starting out you may have to use the <TAB> autocomplete functionality quite often (assuming you have your accounts set up!) but over time you may be able to simply continue hitting <Enter> to accept the best guess because it is correct.

Assuming you didn't escape via <Ctrl> + C, you'll see the path to the exported ledger file as soon as you finish matching the transactions. It'll look like:

```
Jan 06, 2021 9:03:40 PM net.brentwalther.jcf.CsvMatcher run
INFO: Wrote file: yes - /home/brentwalther/Downloads/transactions.ledger
```

Next, get on to [merging it](/jcf/merging.html).