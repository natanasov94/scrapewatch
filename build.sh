#!/bin/bash

docker build \
    --tag natanasov1994/scrapewatch:$1 \
    --target prod-build \
    --file Dockerfile \
    .