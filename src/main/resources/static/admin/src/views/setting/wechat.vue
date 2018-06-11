<template>
    <div class="hello">
    
    
    
        <h1>微信公众号主体设置</h1>
    
        <hr>
    
        <el-form ref="form" :model="wechatInfo" label-width="100px">
    
            <el-form-item label="主体名称">
                <el-input v-model="wechatInfo.name"></el-input>
            </el-form-item>

            <el-form-item label="appid">
                <el-input v-model="wechatInfo.appid"></el-input>
            </el-form-item>


            <el-form-item label="app密钥">
                <el-input v-model="wechatInfo.appsecret"></el-input>
            </el-form-item>

            <el-form-item label="token">
                <el-input v-model="wechatInfo.token"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">保存</el-button>
                <el-button>取消</el-button>
            </el-form-item>
    
        </el-form>
    </div>
</template>

<script>
export default {
  name: "HelloWorld",

  data() {
    return {
      wechatInfo: {
        name: "",
        appid: "",
        appsecret:"",
        token: "",       
      }
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;

  padding: 0;
}

li {
  display: inline-block;

  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
