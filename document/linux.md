# centos 配置

### 1. 网络配置

查看命令 ip a
修改网卡配置文件命令 vi /etc/sysconfig/network-scripts/ifcfg-ens33

* onboot = "yes" 每次启动后自动加载网卡配置
* BOOTPROTO="static" 静态ip地址
* IPADDR="192.168.254.11" 网关与子网掩码
  NETMASK="255.255.255.0"
  GATEWAY="192.168.254.2"
* DNS1=8.8.8.8  dns服务器
  DNS2=8.8.4.4

重启网络命令 service network restart
端口查看命令 netstat -tulp

启动防火墙 systemctl start firewalld

设置Redis 6379 端口：firewall-cmd --zone=public --add-port=6379/tcp --permanent

设置Portainer 9000 端口：firewall-cmd --zone=public --add-port=9000/tcp --permanent

重新加载：firewall-cmd --reload

### 2. 常用设置与命令

关闭图形化界面
systemctl stop gdm.service
systemctl disable gdm.service
设置字符模式
systemctl get-default
systemctl set-default multi-user.target

### 3. 监视

top命令含义解读

如何查找当前内存占用最大的进程
top -b -o +%MEM | head -n 22(行数)


### 4. 文本

set命令学习

-n 只显示匹配文本
-i 原地修改文件