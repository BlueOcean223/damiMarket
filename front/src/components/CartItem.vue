<template>
    <tr>
      <td>
        <input type="checkbox" :value="item.id" :checked="isSelected" @change="toggleSelection" />
      </td>
      <td>
        <div class="product-info">
          <img :src="item.img" alt="商品图片" class="product-image" />
          <span>{{ item.description }}</span>
        </div>
      </td>
      <td class="price">{{ item.price }}元</td>
      <td>
        <button @click="showConfirm()">移除</button>
      </td>
    </tr>

    <ConfirmModal
        v-if="showModal"
        :message="modalMessage"
        :visible="showModal"
        @confirm="handleConfirm"
        @close="showModal = false"
        />

  </template>
  
  <script>
    import ConfirmModal from './ConfirmModal.vue';

  export default {
    name: 'CartItem',
    components:{ConfirmModal},
    props: {
      item: {
        type: Object,
        required: true,
      },
      selectedItems: {
        type: Array,
        required: true,
      },
    },
    data(){
        return{
            showModal: false,
            modalMessage: '',
        }
    },
    computed: {
      isSelected() {
        return this.selectedItems.includes(this.item.id);
      },
    },
    methods: {
      toggleSelection(event) {
        this.$emit('update-selection', { id: this.item.id, selected: event.target.checked });
      },
      showConfirm(){
        this.showModal = true;
        this.modalMessage = `确定要移除${this.item.description}吗？`;
      },
      handleConfirm(){
        this.$emit('remove', this.item.id);
        this.showModal = false;
      },
    },
  };
  </script>
  
  <style scoped>
  .product-info {
    display: flex;
    align-items: center;
    justify-content: center; /*内容居中*/
    margin: 20px 0; /*上、下边距为20px*/
  }
  .product-image {
    width: 80px;
    height: 80px;
    object-fit: cover;
    margin-right: 10px;
  }
  .product-info span {
        font-size: 18px; /* 增大商品名称字体 */
        font-weight: bold; /* 加粗以提高可读性 */
    }

    td {
        font-size: 18px; /* 调整单元格文字的大小，包括价格等 */
    }
    button {
        background-color: transparent;
        border: none;
        color: #ff4a00; /* 使用橙色，让它更显眼 */
        font-weight: bold;
        cursor: pointer;
    }

    button:hover {
        text-decoration: underline; /* 鼠标悬停时，显示下划线 */
    }
    .price{
        color:red;
    }
    tr{
        border-bottom: 1px solid #ddd; /* 增加底部的灰色横线 */
    }
  </style>
  