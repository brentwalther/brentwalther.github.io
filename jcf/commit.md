---
date: 2021-01-06
layout: jcf-documentation
tags:
- java
- bazel
- ledger
- ledger-cli
- jcf
- java cash flow
- commit
- git
title: Making a commit
---
After doing any [merge](/jcf/merging.html), the next step is to diff and commit the update to the master ledger repo.

```
$ cd ~/Development/ledger-private-data
$ git status
On branch master
Your branch is up to date with 'origin/master'.

Changes not staged for commit:
        modified:   master.ledger
```

First, diff it to make sure everything looks good:

```
git diff master.ledger
```

If there are problems, you may edit the master ledger directly in your favorite text editor. That's one of the best parts of plain-text accounting!

You may also want to check the balances:

```
bash ~/Development/ledger-scripts/balance.sh 
```

You can also query specific account registers using `register.sh`. The first argument is a simple substring match for accounts to return in the result. Thus, you can use account number shorthand easily if your accounts contain them:

```
bash ~/Development/ledger-scripts/register.sh 1234
bash ~/Development/ledger-scripts/register.sh Account:Name
```

Once you're satisfied, commit it!

```
cd ~/Development/ledger-private-data
git add master.ledger
git commit -m "Add transactions from Oct-2020 statement for FooBank 1234 account."
```

Periodically, I create a fresh [expense report](/jcf/expense_report.html) to review how the household budget is holding up.
