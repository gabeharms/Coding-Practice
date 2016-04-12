export HADOOP_CLASSPATH=build/classes
hadoop FileSystemCat hdfs://localhost/user/gabe/quangle.txt
hadoop ListStatus hdfs://localhost/ hdfs://localhost/gabe/tom
hadoop FileCopyWithProgress input/docs/1400-8.txt hdfs://localhost/user/gabe/1400-8.txt
