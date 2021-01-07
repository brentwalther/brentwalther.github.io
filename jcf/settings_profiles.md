---
title: JCF settings profiles
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "settings", "profiles", "csv"]
layout: jcf-documentation
date: 2021-01-05
---

To allow JCF to read the transactions from a CSV file, it needs to know a few extra details like the column ordering and date formats in the CSV file. JCF settings profiles allow you to declare these configuration values ahead of time and may be version controled alongside your other data. As part of [setup](/jcf/setup.html#create-the-repo) you should have a `jcf_settings_profiles.textproto` file with a couple of example settings profiles.

I create settings profiles as I go before I match each CSV. Each new settings profile can be re-used so after building up a collection you only need to create new ones for new accounts.

To create one, I open the CSV file and determine the following:

1. The ordering of the data columns, and then converting those to `CsvFieldPosition` messages containing an index and one of [these column types](https://github.com/brentwalther/jcf/blob/92232fcdec0287ed7c7a81643f77aa5883af76ad/src/main/proto/settings_profile.proto#L15-L33) like this:
   ```
   csv_field_positions: {
     position: {
       field: DATE
       column_index: 1
     }
     // ...more...
   }
   ``` 
1. The date format, using *lowercase* `y`, *uppercase* `M`, and *lowercase* `d`. For example:
   ```
   csv_date_format_java: 'yyyy-MM-dd'
   ```
   or
   ```
   csv_date_format_java: 'MM/dd/yy'
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
