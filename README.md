![Blockathon](./TaxChain.jpg)

# Tax Chain

Welcome to the Tax chain  is based on the [CorDapp tutorial](http://docs.corda.net/tutorial-cordapp.html) found on the Corda docsite.

# The Tax Chain

The Tax Chain implements a basic scenario where the tax payer wants to submit the tax, all his details will be pre-filled from the distributed ledger, User has to acknowledge it.

* **Controller** which hosts the network map service and validating notary/government service.
* **Node A** who is the Tax Payer
* **Node B** who is the Bank
* **Node C** who is the Insurance company

## Pre-Requisites

You will need the following installed on your machine before you can start:

* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed and available on your path.
* Latest version of [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (note the community edition is free)
* [h2 web console](http://www.h2database.com/html/download.html)
  (download the "platform-independent zip")
* [Anaconda](https://www.anaconda.com/download/) installed and availalbe on your path.

## Getting Set Up

To get started, clone this repository with:

     git clone https://github.com/corda/cordapp-bill-of-lading.git

To run them, you also need the latest version of TensorFlow. To install it:

     pip install tensorflow

For more details about TensorFlow installation, you can check [TensorFlow Installation Guide](https://www.tensorflow.org/install/) and  see the [getting set up](https://docs.corda.net/getting-set-up.html) page on the Corda docsite.

## Interacting with the CorDapp via HTTP

The CorDapp defines a couple of HTTP API end-points and also serves some
static web content. The end-points allow you to list agreements and add
agreements.

The nodes can be found using the following port numbers, defined in
`build.gradle` and the respective `node.conf` file for each node found
in `kotlin-source/build/nodes/NodeX` or `java-source/build/nodes/NodeX`:

     NodeA: localhost:10007
     NodeB: localhost:10010
     NodeC: localhost:10013

Also, as the nodes start-up they should tell you which host and port the
embedded web server is running on. The API endpoints served are as follows:

     /api/example/me
     /api/example/peers
     /api/example/bls
     /api/example/{COUNTERPARTY}/create-bl
     
The static web content is served from:

     /web/example
     
An BL can be created via accessing the
`create-iou` end-point directly or through the the web form
hosted at `/web/example`.

**NOTE: The content in `web/example` is only available for demonstration
purposes and does not implement any anti-XSS/XSRF security techniques. Do
not copy such code directly into products meant for production use.**

**Submitting an BL via HTTP API:**

To create an BL from NodeA to NodeB, use:

     curl -X PUT 'http://localhost:10007/api/example/create-iou?iouValue=99&partyName=CN%3DNodeB%2CO%3DNodeB%2CL%3DNew%20York%2CC%3DUS'

Note the port number `10007` (NodeA), the IOU value `99` and the counterparty name `CN=NodeB,O=NodeB,L=New York,C=US` 
referenced in the end-point path. This command instructs NodeA to create and send an IOU
to NodeB. Upon verification and completion of the process, both nodes
(but not NodeC) will have a signed, notarised copy of the IOU.

**Submitting an BL via `web/example`:**

Click the "Create BL" button at the top left of the page and enter the BL 
details, e.g.

     Counter-party: CN=NodeB,O=NodeB,L=New York,C=US
     Value:  1

and click "Create BL". The modal dialogue should close.

To check what validation is performed on the BL data, have a look 
at the `Create` class in `BLContract`. For example, Entering a
'Country Code' other than 'UK' will cause the verify function to return an
Exception and you should rceeive an error message in response.

**Viewing the submitted BL:**

Inspect the terminal for the nodes. You should see some activity in the
terminal windows for NodeA and NodeB:

*NodeA:*

     Generating transaction based on new BL.
     Verifying contract constraints.
     Signing transaction with our private key.
     Sending proposed transaction to recipient for review.
     Done

*NodeB:*

     Receiving proposed transaction from sender.
     Verifying signatures and contract constraints.
     Signing proposed transaction with our private key.
     Obtaining notary signature and recording transaction.
     Done

*NodeC:*

     You shouldn't see any activity.


**NOTE: These progress tracking messages are not currently visible in the Nodes, but they are visible on the NodeA WebServer terminal. Running the nodes with log-level DEBUG or TRACE should reveal extra activity when creating a new BL.**

Alternatively, try adding an BL with a delivery date in the past 
or a delivery country other than the UK.

Next you can view the newly created BL by accessing the
vault of NodeA or NodeB:

**Via the HTTP API:**

For NodeA. navigate to
`http://localhost:10007/api/example/bls`. For NodeB,
navigate to `http://localhost:10010/api/example/bls`.

**Via web/example:**

Navigate to `http://localhost:10007/web/example/` and click the refresh
button at the top left-hand side of the page. You should see the newly
created BL on the page.

## Accessing a Node's Database via the h2 Web Console

You can connect to the h2 database to see the current state of the
ledger, among other data such as the network map cache.

Firstly, navigate to the folder where you downloaded the h2 web console
as part of the pre-requisites section, above.

Change directories to the bin folder:

     cd h2/bin
     
Where there are a bunch of shell scripts and batch files. Run the web
console:

**Unix:**

     sh h2.sh
     
**Windows::**

     h2.bat
     
The h2 web console should start up in a web browser tab. To connect we
first need to obtain a JDBC connection string. Each node outputs its
connection string in the terminal window as it starts up. In a terminal
window where a node is running, look for the following string:

     Database connection url is      : jdbc:h2:tcp://xxx.xxx.xxx.xxx:xxxxx/node
     
you can use the string on the right to connect to the h2 database: just
paste it in to the `JDBC URL` field and click *Connect* (leave the default username/password).

You will be presented with a web application that enumerates all the
available tables and provides an interface for you to query them using SQL.

## Further reading

Tutorials and developer docs for CorDapps, Corda are [here](https://docs.corda.net/tutorial-cordapp.html) and TensorFlow are [here](https://www.tensorflow.org/api_docs/)
