*/30 * * * * /bin/sh /opt/projects/Spark-practice/update_code_cron.sh 1>&2 >>/opt/projects/Spark-practice/logs/$(date +\%Y\%m\%d).log
*/30 * * * * /bin/sh /opt/projects/studyspace/update_code_cron.sh 1>&2 >>/opt/projects/studyspace/logs/$(date +\%Y\%m\%d).log

