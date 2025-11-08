## 提交格式：

```
[type](scope): subject
// 空一行
body
```

## 范例:

```
[feat](bl): 新增了退票策略的功能

实现了退票策略的增删改查
修改了电影票的bl
```

## 说明：

type（必需）、scope（可选）、subject（必需）和body（可选）

### (1) type

* type: 用于说明 commit 的类别，只允许使用下面8个标识。
* feat/feature: 新功能
* fix: 修补bug
* doc/docs: 文档
* style: 格式（不影响代码运行的变动）
* refactor: 重构（即不是新增功能，也不是修改bug的代码变动）
* test: 增加测试
* revert: 撤销之前的commit

### (2)scope

 scope用于说明 commit 影响的范围，比如数据层、控制层、视图层等等

### (3)subject

对本次 commit 的简短描述，不超过50个字符
* 以动词开头，使用第一人称现在时，比如change，而不是changed或changes
* 第一个字母小写
* 结尾不加句号（.）

#### (4)Body

对本次 commit 的详细描述，可以分成多行