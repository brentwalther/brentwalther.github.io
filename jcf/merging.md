---
title: Merging
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "merging", "ledger"]
layout: jcf-documentation
date: 2021-01-06
---

The output of [matching](/jcf/matching.html) is a simple `ledger-CLI` compatible text file. It's perfectly fine to open and edit the file using  your favorite text editor. Depending on whether every transaction is properly balanced, you may end up needing to.

Try merging the transactions from this ledger snippet is simple:

```
bash merge.sh ~/Downloads/transactions.ledger
```

if there are any problems with the merge, ledger will print them and the merge will fail. For example:

```
While balancing transaction from "/tmp/tmp.6lhsdICjqC", lines 24148-24149
> 2020-01-30 * NORDSTROM #0010 LYNNWOOD WA
>   Liabilities:Credit Card:Chase 8881                        $-80.97
Unbalanced remainder is:
               $-80.97
Amount to balance against:
               0
Error: Transaction does not balance
```

A few common reasons you'd have to make manual fixes:

- You want to split a transaction with an account not already in your `master.accounts` file. Edit `master.accounts` and add the account.
- The transaction doesn't balance. This happens if you skipped the transaction during [matching](/jcf/matching.html) or you tried to multi-split and made a mistake. Either run the matcher again or edit the ledger snippet and properly balance the transaction, adding new accounts as necessary in `master.accounts`.

If no errors were printed, you can assume the merge was successful. Next, I [diff and make a commit](/jcf/commit.html).
