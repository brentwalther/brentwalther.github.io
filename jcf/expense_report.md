---
title: Expense Report
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "commit", "git"]
layout: jcf-documentation
date: 2021-01-06
---

Once you begin accumulating transactions in your master ledger that split with an `Expense` account, you can trivially create a by-month expense report using Google Sheets.

To do so, export a expense CSV using the script:

```
cd ~/Development/ledger-scripts
bash export-expense-csv.sh  > ~/Downloads/all-expenses.csv
```

then, import that sheet into [this template](https://docs.google.com/spreadsheets/d/1xC_VPJCCO6kgG8kc7n0F3GgRNPTIVf2HK1VQa5BEsDg/edit?usp=sharing) by:

1. Selecting B2 on the sheet 'Raw Data'.
2. Going to File > Import > Upload > Select a file.
3. When the CSV dialog opens, make sure cell B2 is highlighted in the background and you select "Replace data at selected cell" on the dialog.

If you mess up, you can always Undo (Ctrl+Z).

<figure>
  <img alt="Our grains steeping in the pot." src="/img/expense_csv_import_example.png" />
  <figcaption>Importing expense data to the Pivot Table report.</figcaption>
</figure>