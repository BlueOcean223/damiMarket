<template>
    <div class="modal-overlay">
      <div class="modal-content">
        <h2>修改地址</h2>
        <form @submit.prevent="updateAddress">
          <div>
            <label for="shouhuoName">收货人:</label>
            <input type="text" v-model="localshouhuoName" required />
          </div>
          <div>
            <label for="phone_number">电话号码:</label>
            <input type="text" v-model="localPhoneNumber" required />
          </div>
          <div>
            <label for="address">收货地址:</label>
            <input type="text" v-model="localAddress" required />
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
    name: 'ChangeAddressModal',
    props: {
        shouhuoName: {
            type: String,
            required: true
        },
        phone_number: {
            type: String,
            required: true
        },
        address: {
            type: String,
            required: true
        }
    },
    data(){
        return{
            localshouhuoName: this.shouhuoName,
            localPhoneNumber: this.phone_number,
            localAddress: this.address
        }
    },

    methods: {
      updateAddress() {
        const newAddress = {
          shouhuoName: this.localshouhuoName,
          phone_number: this.localPhoneNumber,
          address: this.localAddress
        };
        this.$emit('update', newAddress);
      }
    }
  };
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .modal-content {
    background: white;
    padding: 30px;/* 增加padding以增加内边距 */
    border-radius: 8px;
    width: 400px; /* 调整宽度以使其更宽 */
    text-align: left;
  }
  .modal-content h2 {
      text-align: center;/* 标题居中 */
      margin-bottom: 20px; /* 增加底部的间距，拉开标题和表单之间的距离 */
  }
  .modal-content form div{
    margin-bottom: 15px;/* 每个表单项增加底部的间距，使它们之间更宽松 */
  }
  .modal-actions {
    margin-top: 30px;/* 增加与表单项之间的距离 */
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
  