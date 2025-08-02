<template>
    <div class="modal-overlay">
      <div class="modal-content">
        <h2>新增收货地址</h2>
        <form @submit.prevent="saveAddress">
          <div>
            <label for="username">收货人:</label>
            <input type="text" v-model="shouhuoName" required />
          </div>
          <div>
            <label for="phone_number">电话号码:</label>
            <input type="text" v-model="phone_number" required />
          </div>
          <div>
            <label for="address">收货地址:</label>
            <input type="text" v-model="address" required />
          </div>
          <div class="modal-actions">
            <button type="submit">保存</button>
            <button type="button" @click="$emit('close')">取消</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'AddAddressModal',
    data() {
      return {
        shouhuoName: '',
        phone_number: '',
        address: ''
      };
    },
    methods: {
      saveAddress() {
        const newAddress = {
          shouhuoName: this.shouhuoName,
          phone_number: this.phone_number,
          address: this.address
        };
        this.$emit('save', newAddress);
      }
    }
  };
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;/* 覆盖整个视窗 */
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);/* 半透明黑色背景，调暗效果 */
    display: flex;
    align-items: center;
    justify-content: center;
    outline: none; /* 去掉聚焦时的边框 */
    user-select: none; /* 禁止文本被选中 */
  }
  
  .modal-content {
    background: white;
    padding: 30px;
    border-radius: 8px;
    width: 400px;
    text-align: left;
  }
  .modal-content h2 {
      text-align: center;
      margin-bottom: 20px;
  }
  .modal-content form div{
    margin-bottom: 15px;
  }
  .modal-actions {
    margin-top: 30px;
    display: flex;
    justify-content: space-between;
  }
  button{
    background-color: #77bbe9;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
  }
  button:hover{
    background-color: rgb(167, 161, 161);
  }
  </style>
  