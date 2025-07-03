<template>
    <tr>
      <td>
        <div class="product-info">
          <img :src="item.img" alt="商品图片" class="product-image" />
          <span>{{ item.description }}</span>
        </div>
      </td>
      <td>{{ item.address }}</td>
      <td>
        <div v-if="item.status !== '完成'">
          <button @click="showConfirmModal('cancel', item.id)">退货</button>
          <button @click="showConfirmModal('confirm', item.id)">收货</button>
        </div>
      </td>
      <td>{{ item.status }}</td>
      <td>
        <button @click="showConfirmModal('delete', item.id)" class="del-btn">删除</button>
      </td>

      <ConfirmModal
        v-if="showModal"
        :message="modalMessage"
        :visible="showModal"
        @confirm="handleConfirm"
        @close="showModal = false"
        />
    </tr>
  </template>
  
  <script>
  import ConfirmModal from './ConfirmModal.vue';
  export default {
    name: 'OrderItem',
    components: { ConfirmModal },
    props: {
      item: {
        type: Object,
        required: true,
      },
    },
    data(){
        return{
            showModal: false,
            action: '',
            actionId: null,
            modalMessage: '',
        };
    },
    methods: {
        showConfirmModal(action, id) {
            this.action = action;
            this.actionId = id;
            this.showModal = true;
            if(action === 'cancel'){
                this.modalMessage = '您确定要退货吗？';
            }else if(action === 'delete'){
                this.modalMessage = '您确定要删除订单吗？';
            }else{
                this.modalMessage = '您确定收到货了吗？';
            }
        },
        handleConfirm(){
            if(this.action === 'confirm'){
                this.$emit('confirm', this.actionId);
            }else{
                this.$emit('cancel', this.actionId);
            }
            this.showModal = false;
        },
    },
  };
  </script>
  
  <style scoped>
  .product-info {
    display: flex;
    align-items: center;
    outline: none; /* 去掉聚焦时的边框 */
    user-select: none; /* 禁止文本被选中 */
  }
  .product-image {
    width: 100px;
    height: 100px;
    object-fit: cover;
    margin-right: 10px;
  }
  button {
    background-color: #00aeff;
    color: white;
    border: none;
    padding: 5px 15px;
    font-size: 14px;
    cursor: pointer;
    margin-right: 5px;
    border-radius: 5px;
  }
  /* 选中时的样式 */
  button:hover {
    background-color: #080808;
  }
  td{
    font-size: 18px;
    border-bottom: 1px solid #ddd;
  }
  .del-btn {
      background-color: #f89696;
  }
  </style>
  