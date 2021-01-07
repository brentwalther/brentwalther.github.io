---
title: Merging
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "merging", "ledger"]
layout: jcf-documentation
date: 2021-01-06
---

The output of [matching](/jcf/matching.html) is a simple `ledger` compatible text file. It's perfectly fine to make local edits with your favorite text editor and depending on whether or not you matched all the transaction, you may have to!

Merging the transactions from this ledger snippet is simple:

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

If the account is missing, all you need to do is add it to your `master.accounts` file. If the transaction doesn't balance, simply add the missing splits creating new accounts if necessary.

After a successful merge, I move on to [making a commit](/jcf/commit.html).