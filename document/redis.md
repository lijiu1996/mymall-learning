# redis 部署经验

### 配置redis.conf 并且注册成系统服务

### 解决部署redis被防火墙问题

查询 firewall-cmd --query-port=6379/tcp
添加 firewall-cmd --add-port=6379/tcp --permanent
刷新 firewall-cmd --reload
重启redis systemctl restart redis