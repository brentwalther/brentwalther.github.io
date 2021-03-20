---
title: Setup
tags: ["java", "bazel", "ledger", "ledger-cli", "jcf", "java cash flow", "setup", "install"]
layout: jcf-documentation
date: 2020-12-31
---

To get started, you need to install a few things:

### Tools

#### `bazel`

JCF is built/run with [`bazel`](https://docs.bazel.build/getting-started.html). You should follow the instructions there to get it installed on your machine. I have not tested `bazel` on Windows or Mac but assume it works.

> Since JCF is a Java application you must also have a JDK installed. Instructions to do this are included in the `bazel` setup documentation.

#### `ledger`

You need to install the [`ledger-CLI`](https://www.ledger-cli.org/download.html) to query and manage your general ledger. I've successfully compiled it from source on Ubuntu Linux but if you don't care to go to that trouble, the website has precompiled binaries.

#### `git`

You'll need git to clone JCF and my ledger scripts repository. Get it at [https://git-scm.com/](https://git-scm.com/).

### Repos

For convenience I like to structure all my repositories in a special directory. Usually `~/Development` on my personal workstations.

#### Clone the tool Github repos {#clone-the-repos}

```
cd ~/Development

git clone git@github.com:brentwalther/jcf.git

git clone git@github.com:brentwalther/ledger-scripts.git
```

#### Create a personal repo for your ledger data {#create-the-repo}

Since the ledger-CLI general ledger format is all text we can use `git` to diff, update, and maintain snapshots of it. 

In the below examples, I'm using a directory called `~/ledger-private-data` as the repo:

1. Copy the master accounts template:
   ```
   cd ~/Development
   mkdir ledger-private-data
   cp ./jcf/examples/master.accounts ./ledger-private-data/master.accounts
   ```
1. Update `ledger-private-data/master.accounts` using your favorite text editor. It is neither required nor recommended to set up all accounts now. Instead, inspect the expense and income account categories to determine whether they're sufficient for your bookkeeping/budgeting purposes. After that, set up just one actual account to start (e.g. a `Liabilities:Credit Card:Bank 1234` or `Assets:Current Assets:My Checking 1234` account).
1. Go ahead and create an empty `master.ledger` where we'll merge other ledgers in to:
   ```
   cd ~/Development
   touch ./ledger-private-data/master.ledger
   ```
1. Create a `vars.sh` for the ledger scripts:
   ```
   cd ~/Development
   cp ./ledger-scripts/examples/vars.sh ./ledger-private-data/vars.sh
   ```
1. Update the `vars.sh` file. To start, you may fill in only the ledger executable, master ledger, and master accounts paths. You may leave out the commodities file if desired.
1. Add an initial settings profiles files:
   ```
   cd ~/Development
   cp ./jcf/examples/settings_profiles.textproto ./ledger-private-data/jcf_settings_profiles.textproto
   ```
1. Initiliaze and create the initial commit in the repo:
   ```
   cd ~/Development/ledger-private-data
   git init .
   git add -A
   git commit -m "Initial commit."
   ```
1. (optional) Upload the repo to some private remote. I use Github.

### Update the paths in `/ledger-scripts/vars.sh`

Edit the `/ledger-scripts/vars.sh` file to point to your personal `vars.sh` you created in your private data repo using your favorite text editor. For example:

```
vim ~/Development/ledger-scripts/vars.sh
```

Then, edit your personal `vars.sh` to contain the paths for all the data and tools on your own local environment. You should use **`/absolute/path/names`**:

```
vim ~/Development/ledger-private-data/vars.sh
```

## Moving on

After you've cloned `jcf`, installed `ledger`, and established a repository (as above), you've got everything you need to manage a general purpose version-controlled financial ledger.

Move on to [importing a CSV](/jcf/matching.html).
