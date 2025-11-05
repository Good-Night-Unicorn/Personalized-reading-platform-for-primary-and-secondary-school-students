const base = {
    get() {
        return {
            url : "http://localhost:8080/ssmixgl9940/",
            name: "ssmixgl9940",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/ssmixgl9940/front/h5/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于JAVA的中小学生个性化阅读平台的设计与实现"
        } 
    }
}
export default base
