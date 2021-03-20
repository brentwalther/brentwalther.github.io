---
title: Expense Report
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "commit", "git"]
layout: jcf-documentation
date: 2021-01-06
---

New to ledger? [Start here](/jcf).

After you've accumulated transactions in your master ledger that split with `Expense` accounts, you can trivially create a by-month expense report using Google Sheets.

1. Export an expense CSV using the [`export-expenses-csv.sh`](/jcf/setup.html#clone-the-repos) script:

   ```
   cd ~/Development/ledger-scripts
   bash export-expense-csv.sh  > ~/Downloads/all-expenses.csv
   ```
1. Open the [expense report Google Sheets template](https://docs.google.com/spreadsheets/d/1xC_VPJCCO6kgG8kc7n0F3GgRNPTIVf2HK1VQa5BEsDg/edit?usp=sharing)
1. Selecting B2 on the sheet 'Raw Data'.
1. Going to File > Import > Upload > Select a file.
1. When the CSV dialog opens, make sure cell B2 is highlighted in the background and you select "Replace data at selected cell" on the dialog.

If you mess up, you can always Undo (`<Ctrl> + Z`).

<figure>
  <img alt="Our grains steeping in the pot." src="/img/expense_csv_import_example.png" />
  <figcaption>Importing expense data to the Pivot Table report.</figcaption>
</figure>
