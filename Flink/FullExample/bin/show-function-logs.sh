#!/bin/bash

kubectl logs -f -l component=hello -n statefun
