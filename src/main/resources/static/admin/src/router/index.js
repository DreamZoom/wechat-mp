import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

import SettingWechat from '@/views/setting/wechat'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/setting/wechat',
      name: 'SettingWechat',
      component: SettingWechat
    }
  ]
})
