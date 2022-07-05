#!/bin/sh
export LANG=zh_CN.UTF-8
source_path=/opt/opensource/Spark-practice

# 0,30 * * * * /bin/sh /opt/opensource/studyspace/update_code_cron.sh 2>&1 >/opt/opensource/studyspace/$(date +\%Y\%m\%d).log
echo ''
echo "update time: `date`"
echo '**********************************START************************************************' 1>&2
cd ${source_path}
echo `date`>>tip.txt
git status
if [ $? -eq 0 ]; then
    	echo "提交代码到github"
	git add --all && git commit -m "update file"
	git push -u origin master
    	echo "提交代码成功！！！"
   else
   	 echo "提交代码失败！！！"
fi
git stash && git pull -u origin master
if [ $? -eq 0 ]; then
    echo "拉取最新的代码！！！"
   else
    echo "拉取最新的代码失败！！！"
   fi
echo '***********************************END**************************************************' 1>&2
