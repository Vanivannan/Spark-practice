#!/bin/sh
export LANG=zh_CN.UTF-8
source_path=/opt/opensource/Spark-practice

# 0,30 * * * * /bin/sh /opt/opensource/studyspace/update_code_cron.sh 2>&1 >/opt/opensource/studyspace/$(date +\%Y\%m\%d).log
echo ''
echo "update time: `date`"
echo '**********************************************************************************' 1>&2
cd ${source_path}
echo "update code date is: `date`" >>tip.txt
git add --all && git commit -m "update file"
git push origin master
git stash && git pull -u origin master
echo '**********************************************************************************' 1>&2
