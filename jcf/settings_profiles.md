---
title: JCF settings profiles
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "settings", "profiles", "csv"]
layout: jcf-documentation
date: 2021-01-05
---

To allow JCF to read the transactions from a CSV file, it needs to know a few extra details like the column ordering and date formats in the CSV file. JCF settings profiles allow you to declare these configuration values ahead of time and may be version controled alongside your other data. As part of [setup](/jcf/setup.html#create-the-repo) you should have a `jcf_settings_profiles.textproto` file with a couple of example settings profiles.

I create settings profiles as I go before I match each CSV. Each new settings profile can be re-used so after building up a collection you only need to create new ones for new accounts.

To create one, I open the CSV file and determine the following:

```
settings_profile: {
  // First, the name of the settings profile. This is how you'll refer
  // to it when matching the CSV.
  name: 'ally'

  // Next, the columns where all the interesting data resides, with
  // columns starting at index *0* ! List them as `position` objects as
  // with the index and field type for each field.
  //
  // Valid field types are:
  //   DATE - The date on which a transaction occurred.
  //   DESCRIPTION - The description of the transaction in question.
  //   DEBIT - The debit amount of the transaction if credit/debit amounts
  //     are separate.
  //   CREDIT - The credit amount of the transaction, if credit/debit
  //     amounts are separate.
  //   AMOUNT - The amount of the transaction interpreted literally.
  //   NEGATED_AMOUNT - The amount of the transaction but negated (i.e.
  //     positive becomes negative and visa versa). You'd choose this if
  //     the AMOUNT type caused your splits to balance upside down.
  csv_field_positions: {
    position: {
      field: DATE
      column_index: 0
    }
    position: {
      field:DESCRIPTION
      column_index: 2
    }
    position: {
      field: AMOUNT
      column_index: 3
    }
  }

  // Then, the date format for the dates in the DATE column. The format
  // is interpreted like a java.time.format.DateTimeFormatter pattern. For
  // example, year is `y`, month is `M`, and day is `d` for patterns like:
  // - 'yyyy-MM-dd' - like 2020-12-28
  // - 'MM/dd/yy'   - like 12/28/20
  csv_date_format_java: 'MM/dd/yy'

  // Lastly, the name of the account as it appears in your master accounts
  // file from which these transactions are coming from.
  csv_account_name: 'Assets:Current Assets:Checking 1234'
```

Lastly, I sometimes have a separate profile for account nicknames but also sometimes lump a profile together with all settings set at once! JCF will let you know if a setting isn't specified correctly.

### Master ledger accounts {#accounts}

JCF works best when you have at the very least listed the expected accounts in your `master.ledger`. You may optionally have a separate `master.accounts` file containing an explicit account listing.

As part of [setup](/jcf/setup.html#create-the-repo) you should have set up a master ledger with a bunch of income and expense accounts as part of setup. If you need to add more, simply edit your `master.ledger` to add explicit account lines like:

```
account Expenses:My Expense Category:Sub Category 
```

or

```
account Assets:Current Assets:My Bank 1234
```
