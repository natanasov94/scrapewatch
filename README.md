# Scrapewatch

Small web crawler/scraper designed to be deployer with containerization in mind.

The applications is deployed in a kubernetes cluster as a service, and monitored by:
- Kibana
- Logstash
- Filebeat
- Prometheus

Additionally, for the time being, the idea is to also have a postgres db in the background
