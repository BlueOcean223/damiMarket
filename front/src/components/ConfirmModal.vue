<template>
    <div class="modal-overlay" v-if="visible">
      <div class="modal-content">
        <h3>{{ message }}</h3>
        <div class="modal-actions">
          <button @click="$emit('confirm')">确定</button>
          <button @click="$emit('close')">取消</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ConfirmModal',
    props: {
      visible: {
        type: Boolean,
        required: true
      },
      message: {
        type: String,
        required: true
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
    padding: 20px;
    border-radius: 8px;
    text-align: center;
  }
  .modal-actions {
    margin-top: 20px;
    display: flex;
    justify-content: space-around;
  }
  button {
    background-color: #00aeff;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 5px;
  }
  button:hover {
    background-color: #008ecf;
  }
  </style>
  