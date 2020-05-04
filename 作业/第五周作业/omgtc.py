# -*- coding: utf-8 -

from __future__ import print_function
import sys
import re
from allpairspy import AllPairs


def fread(file_name):
    # 读取文档写入list
    f = open(file_name, "r")  # 设置文件对象
    data = f.readlines()  # 直接将文件中按行读到list里
    f.close()  # 关闭文件
    return data


def parse(data):
    # 解析/过滤读取的文件成为可识别格式
    parameters = []
    for line in data:
        a = line.strip('\n')  # 去除每行尾的\n
        b = a.replace(' ', '')  # 去除每行中的所有空格
        c = b[b.rfind(':', 1) + 1:]  # 去除:前的所有字符
        d = c[c.rfind('：', 1) + 1:]  # 去除：前的所有字符
        e = re.split('[,，]', d)  # 根据,或，符号拆分字符
        parameters.append(e)
    return parameters


def allpairs(parameters):
    
    print("PAIRWISE:")
    for i, pairs in enumerate(AllPairs(parameters)):
        print("{:2d}: {}".format(i, pairs))


if __name__ == "__main__":
    file_name = sys.argv[1]
    parameters = parse(fread(file_name))
    allpairs(parameters)


