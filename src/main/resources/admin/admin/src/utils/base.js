const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootarkc6v1u/",
            name: "springbootarkc6v1u",
            // 退出到首页链接
            indexUrl: ''
        };
    },
    getProjectName(){
        return {
            projectName: "测试管理系统"
        } 
    }
}
export default base
