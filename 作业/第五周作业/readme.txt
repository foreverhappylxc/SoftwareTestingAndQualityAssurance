Linux平台下, 采用Python, 来实现正交设计测试用例自动生成工具.
1. 安装环境库: pip3 install allpairspy
2. 执行命令: 	python3 omgtc.py 文件名称 > 文件名称 //输出到文件中
             	python3 omgtc.py 文件名称 //控制台输出
3. 例子: 
		python3 omgtc.py 1.txt > result.txt
		python3 omgtc.py 1.txt


可能是Python算法的问题, 相同的输入文件, 产生的输出会比PICT多一个.
