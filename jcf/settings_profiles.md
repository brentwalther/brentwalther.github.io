---
date: 2021-01-05
layout: jcf-documentation
tags:
- java
- bazel
- ledger
- ledger-cli
- ledger cli
- jcf
- java cash flow
- settings
- profile
- convert
- csv
- ally
- checking
- savings
- credit card
- capital one
- venture
- performance savings
- chase
- sapphire reserve
- amex
- american express
- blue cash preferred
title: JCF settings profiles
---
To allow JCF to read the transactions from a CSV file, it needs to know a few extra details like the column ordering and date formats in the CSV file. JCF settings profiles allow you to declare these configuration values ahead of time and may be version controled alongside your other data. As part of [setup](/jcf/setup.html#create-the-repo) you should have a `jcf_settings_profiles.textproto` file with a couple of example settings profiles.

I create settings profiles as I go before I match each CSV. Each new settings profile can be re-used so after building up a collection you only need to create new ones for new accounts.

To see some examples, consider looking at:

- [Ally Checking and Savings](#ally-checking-savings) settings profile.
- [Capital One Venture Credit Card](#capital-one-venture) settings profile.
- [Capital One Performance Savings](#capital-one-performance-savings) settings profile.
- [American Express Blue Cash Preferred Credit Card](#amex-blue-cash-preferred) settings profile.
- [Chase Sapphire Reserve Credit Card](#chase-sapphire-reserve) settings profile.

You can just as easily create a custom one. I open the CSV file and define one myself by looking at the date format, column indexes of the data, and the [account](#accounts) if applies to using the following template:

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

As part of [setup](/jcf/setup.html#create-the-repo) you likely set up a master ledger with an explicit listing of income and expense accounts. As you begin cataloging transactions from more banks and financial institutions, just edit your `master.ledger` to new account listings like:

```
account Expenses:My Expense Category:Sub Category 
```

or

```
account Assets:Current Assets:My Bank 1234
```

### JCF Settings profile for converting Ally Checking and Savings CSVs {#ally-checking-savings}

As of March 2021, I use the following settings profile to [match and convert](/jcf/matching.html) CSV transaction exports from Ally Checking and Savings accounts to a ledger-cli compatible file which I can then [merge](/jcf/merging.html) in to my `master.ledger`. Simply replace the example account number suffix (`1234`) with your own:

```
settings_profile: {
  name: '1234'
  csv_date_format_java: 'yyyy-MM-dd'
  csv_field_positions: {
    position: {
      field: DATE
      column_index: 0
    }
    position: {
      field: AMOUNT
      column_index: 2
    }
    position: {
      field: DESCRIPTION
      column_index: 4
    }
  }
  csv_account_name: 'Assets:Current Assets:Ally Checking 1234'
}
```

### JCF Settings profile for converting Capital One Venture Credit Card CSVs {#capital-one-venture}

As of March 2021, I use the follow settings profile to [match and convert](/jcf/matching.html) CSV transactions exports from my Capital One Venture Credit Card account to a ledger-cli compatible file which I can then [merge](/jcf/merging.html) in to my `master.ledger`. Simply replace the example account number suffix (`1234`) with your own:

```
settings_profile: {
  name: '1234'
  csv_account_name: 'Liabilities:Credit Card:Capital One Venture 1234'
  csv_date_format_java: 'yyyy-MM-dd'
  csv_field_positions: {
    position: {
      field: DATE
      column_index: 1
    }
    position: {
      field: DESCRIPTION
      column_index: 3
    }
    position: {
      field: DEBIT
      column_index: 5
    }
    position: {
      field: CREDIT
      column_index: 6
    }
  }
}
```

### JCF Settings profile for converting Capital One Performance Savings CSVs {#capital-one-performance-savings}

As of March 2021, I use the follow settings profile to [match and convert](/jcf/matching.html) CSV transactions exports from my Capital One Performance Savings account to a ledger-cli compatible file which I can then [merge](/jcf/merging.html) in to my `master.ledger`. Simply replace the example account number suffix (`1234`) with your own:

```
ettings_profile: {
  name: '1234'
  csv_account_name: 'Assets:Current Assets:Capital One Savings 1234'
  csv_date_format_java: 'MM/dd/yy'
  csv_field_positions: {
    position: {
      field: DATE
      column_index: 1
    }
    position: {
      field: AMOUNT
      column_index: 2
    }
    position: {
      field: DESCRIPTION
      column_index: 4
    }
  }
}
```

### JCF Settings profile for converting American Express Blue Cash Preferred Credit Card CSVs {#amex-blue-cash-preferred}

As of March 2021, I use the follow settings profile to [match and convert](/jcf/matching.html) CSV transactions exports from my American Express Blue Cash Preferred Credit Card account to a ledger-cli compatible file which I can then [merge](/jcf/merging.html) in to my `master.ledger`. Simply replace the example account number suffix (`12345`) with your own:

```
settings_profile: {
  name: '12345'
  csv_account_name: 'Liabilities:Credit Card:American Express Blue Cash Preferred 12345'
  csv_date_format_java: 'MM/dd/yyyy'
  csv_field_positions: {
    position: {
      field: DATE
      column_index: 0
    }
    position: {
      field: DESCRIPTION
      column_index: 1
    }
    position: {
      field: NEGATED_AMOUNT
      column_index: 4
    }
  }
}
```

### JCF Settings profile for converting Chase Sapphire Reserve Credit Card CSVs {#chase-sapphire-reserve}

As of March 2021, I use the follow settings profile to [match and convert](/jcf/matching.html) CSV transactions exports from my Chase Sapphire Reserve Credit Card account to a ledger-cli compatible file which I can then [merge](/jcf/merging.html) in to my `master.ledger`. Simply replace the example account number suffix (`1234`) with your own:

```
settings_profile: {
  name: '1234'
  csv_account_name: 'Liabilities:Credit Card:Chase 1234'
  csv_date_format_java: 'MM/dd/yyyy'
  csv_field_positions: {
    position: {
      field: DATE
      column_index: 1
    }
    position: {
      field: DESCRIPTION
      column_index: 2
    }
    position: {
      field: AMOUNT
      column_index: 5
    }
  }
}
````
