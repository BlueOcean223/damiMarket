<template>
  <MenuBar/>
  <div class="address-management">
    <h1>地址管理</h1>
    <button @click="showAddAddressModal = true">新增收货地址</button>
    <div class="address-list">
      <div v-for="(address, index) in addresses" :key="index" class="address-item">
        <p>收货人：{{ address.shouhuoName }}</p>
        <p>电话号码：{{ address.phone_number }}</p>
        <p>收货地址：{{ address.address }}</p>
        <div class="button-group">
            <button @click="update(index)">修改</button>
            <button @click="showConfirmerModal(index,address)">删除</button>
        </div>
        
      </div>
    </div>
    <AddAddressModal
      v-if="showAddAddressModal"
      @close="showAddAddressModal = false"
      @save="addAddress"
    />
    <ChangeAddressModal
      v-if="showChangeAddressModal"
      :shouhuoName = "addresses[updateIndex].shouhuoName"
      :phone_number = "addresses[updateIndex].phone_number"
      :address = "addresses[updateIndex].address"
      @close="showChangeAddressModal = false"
      @update="updateAddress"
      />

      <ConfirmModal
        v-if="showModal"
        :message="modalMessage"
        :visible="showModal"
        @confirm="handleConfirm"
        @close="showModal = false"
        />
  </div>
</template>

<script>
import AddAddressModal from '@/components/AddAddressModal.vue';
import MenuBar from '@/components/MenuBar.vue';
import ChangeAddressModal from '@/components/ChangeAddressModal.vue';
import ConfirmModal from '@/components/ConfirmModal.vue';
import { getAll, add, remove, update } from '@/api/address.js';

export default {
  name: 'MyAddressPage',
  components: { AddAddressModal ,MenuBar, ChangeAddressModal, ConfirmModal},
  data() {
    return {
      showAddAddressModal: false,
      showChangeAddressModal: false,
      addresses: [],
      updateIndex: -1,
      updateId: null,
      showModal: false,
      modalMessage: '您确定要删除该地址吗？',
      delIndex: null,
      delAddress: null,
    };
  },
  mounted() {
    this.fetchAddresses();
  },
  methods: {
    async fetchAddresses() {
      try {
        const username = localStorage.getItem('username');
        const response = await getAll(username);
        if (response.data.success) {
          this.addresses = response.data.data;
        }
      } catch (error) {
        console.error('Error fetching addresses:', error);
      }
    },
    async addAddress(newAddress) {
      try {
        const username = localStorage.getItem('username');
        const data = {
            username: username,
            shouhuoName: newAddress.shouhuoName,
            phone_number: newAddress.phone_number,
            address: newAddress.address
        };
        const response = await add(data);
        if (response.data.success) {
          this.addresses.push(newAddress);
        }
      } catch (error) {
        console.error('Error adding address:', error);
      }
      this.showAddAddressModal = false;
    },
    async deleteAddress(index,address) {
        try{
            const data = {
                username: address.username,
                shouhuoName: address.shouhuoName,
                phone_number: address.phone_number,
                address: address.address
            };
            const response = await remove(data);
            if(response.data.success){
                this.addresses.splice(index, 1);
            }else{
                console.erroe(response.data.message);
            }
        }catch(error){
            console.error('Error deleting address:', error);
        }
    },
    async updateAddress(newAddress) {
        try{
            const username = localStorage.getItem('username');
            const data = {
                id: this.updateId,
                username: username,
                shouhuoName: newAddress.shouhuoName,
                phone_number: newAddress.phone_number,
                address: newAddress.address,  
            };
            const response = await update(data);
            if(response.data.success){
                // 更新地址
                this.addresses[this.updateIndex].username = newAddress.username;
                this.addresses[this.updateIndex].shouhuoName = newAddress.shouhuoName;
                this.addresses[this.updateIndex].phone_number = newAddress.phone_number;
                this.addresses[this.updateIndex].address = newAddress.address;

                // 关闭模态框
                this.updateIndex = -1;
                this.updateId = null;
                this.showChangeAddressModal = false;
            }else{
                console.error(response.data.message);
            }
        }catch(error){
            console.error('Error updating address:', error);
        }
    },
    update(index){
        this.updateIndex = index;
        this.showChangeAddressModal = true;
        this.updateId = this.addresses[index].id;
    },
    showConfirmerModal(index,address){
        this.showModal = true;
        this.delIndex = index;
        this.delAddress = address;        
    },
    handleConfirm(){
        this.deleteAddress(this.delIndex,this.delAddress);
        this.showModal = false;
    },
  },
};
</script>

<style scoped>
.address-management {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  outline: none; /* 去掉聚焦时的边框 */
  user-select: none; /* 禁止文本被选中 */
}

.address-list {
  margin-top: 20px;
}

.address-item {
  border: 1px solid #ddd;
  padding: 10px;
  margin-bottom: 10px;
  text-align: left;
  position: relative;/* 使用相对定位，为右侧对齐提供支持 */
}
button {
    background-color: #77bbe9;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    margin-left: 10px; /* 按钮之间的间距 */
}
button:hover {
    background-color: #59a1db;
}
.button-group {
    position: absolute;
    right: 10px; /* 按钮距离右边的距离 */
    top: 50%;
    transform: translateY(-50%); /* 垂直居中 */
}

</style>
