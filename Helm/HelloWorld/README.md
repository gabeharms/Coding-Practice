### Summary

This is a project that provides an introduction to Helm and a good starting place for learning.

### Overview

Deploying applications to Kubernetes – the powerful and popular container-orchestration system – can
be complex. Setting up a single application can involve creating multiple interdependent Kubernetes
resources – such as pods, services, deployments, and replicasets – each requiring you to write a 
detailed YAML manifest file.

Helm is a package manager for Kubernetes that allows developers and operators to more easily 
package, configure, and deploy applications and services onto Kubernetes clusters.

Helm is now an official Kubernetes project and is part of the Cloud Native Computing Foundation, a 
non-profit that supports open source projects in and around the Kubernetes ecosystem.

Most programming languages and operating systems have their own package managers to assist with the 
installation and maintenance of software. Helm provides the same basic feature set as many of the 
package managers you may already be familiar with such as Debian's apt, or Python's pip.

Helm packages are called charts, and they consist of a few YAML configuration files and some 
templates that are rendered into Kubernetes manifest files. Here is the basic file structure of a
chart:
```
package-name/
  charts/
  templates/
  Chart.yaml
  LICENSE
  README.md
  requirements.yaml
  values.yaml
  ```
These directories and files have the following functions:
* charts/  - Manually managed chart dependencies are placed in this directory, though it is 
    typically better to use 'requirements.yaml' to dynamcially link dependencies
* templates/  - Contains template files that are combined with configuration values from (values.yaml)
    and rendered into the Kubernetes manifests. The templates use the Go language template format
* Chart.yaml  - Containes metadata about the chart, such as chart name and version, maintainer info,
    relevant website and search keywords
* README.MD  - Info for users about the chart
* requirements.yaml  - lists the chart's dependencies
* values.yaml  - default configuration values for the chart

The helm command line tool can install repositories of charts, which can then be installed to a 
local directory. Helm comes prefconfigured with a default chart repository referred to as "stable"

A chart is usually installed with default configuration values in the 'values.yaml'. While some charts
may be fully deployable with these values, you will typically need to override some of these values.

A helm chart deployed with a particular configuration is called a 'release'. Helm combines the
templates with the 'values.yaml' to render a Kubernetes mantifest that are then deployed via
the Kubernetes API. This creates a release, a specific configuration and deployment of a particular
chart.

If you can't find an existing chart for the software you are deploying, you may want to create your
own. Helm can output the scaffold of a chart directory with the following:
> helm create <chart-name>

This will create a folder with the files and directories discussed above. From there you'll want
to fill out your chart's metadata in Chart.yaml and put your Kubernetes manifest files into the 
templates directory. You'll then need to extract relevant configuration variables out of you manifests
and into your 'values.yaml'.

###### Tiller

Helm is our package manager for Kubernetes and our client tool. We use helm CLI to for our commands,
but the other part of the puzzle is Tiller

Tiller is the service that actually communicates with the Kubernetes API to manage our Helm packages.
It is the Helm service side component installed directly into your Kubernetes cluster.

When launching your cluster using helm:
> helm init

The kubernetes cluster is created and the helm backend (Tiller) is automatically created as a deployment
into the cluster.


In version 3 of helm, Tiller was removed and is no longer required to run on your cluster in order to
use Helm. Most of this tiller section is no longer relevant.


### Tutorial

Install Helm
> brew install helm

Initialize a Helm Chart Repository
> helm repo add stable https://charts.helm.sh/stable

Now that the repository is installed, you can list out which charts are available
to be installed:
> helm search repo stable

The following commands allow you to inspect a chart:
> helm show chart stable/mysql
Or
> helm show all stable/mysql

Install a chart:
> helm install stable/mysql

In the above command, the "stable/mysql" chart was released, and the name of our new release
is "mysql-1609769004". Whenever you install a chart, a new release is created. So one chart can be installed
multiple times into the same cluster. And each can be independently managed and upgraded.

Its easy to see what has been released using helm:
> helm ls

The following command will show a lost of all deployed releases:
> helm list

To uninstall a release:
> helm uninstall mysql-1609769004

This will uninstall the release from Kubernetes, which will remove all resources associated with
the release as well as the release history (unless --keep-history is provided)

You can check on the status of a release as follows:
> helm status mysql-1609769004


