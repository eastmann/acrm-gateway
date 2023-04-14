<template>

  <v-data-table
      :headers="headersOneLevel"
      :items="items"
      :options="options"
      :search="searchProp"
      :page="page"
      :items-per-page="itemsPerPage"
      hide-default-header
      hide-default-footer
      :fixed-header="fixedHeaderProp"
      :height="heightProp"
      :custom-sort="customSortProp"
      no-results-text="Нет данных"
      no-data-text="Нет данных"
  >
    <template v-slot:header>
      <base-data-table-header
          :headers-prop="headers"
          :options-prop="options"
      />

<!--      <tr v-if="showFilter">-->
<!--        <td v-for="h in headers" :key="h.value">-->
<!--          <v-text-field  v-if="h.filterable"-->
<!--                         type="text" v-model="h.value"-->
<!--          ></v-text-field>-->
<!--        </td>-->
<!--      </tr>-->
    </template>

    <template v-slot:item="{ item : item }">
      <tr @click="handlerClick(item)"
          :class="{'clickable' : isClickableRow}">
        <template v-for="header in headersOneLevel">
          <td v-if="!header.hidden" :key="header.value"
              :class="tdClass(header)"
              v-html="item[header.value]"
          >
          </td>
        </template>
      </tr>
    </template>

    <template v-if="showPaginationProp" v-slot:footer="{ props: { pagination } }">
      <base-pagination
        :total="pagination.itemsLength"
        :page-changed="pageChanged"
        :per-page-changed="perPageChanged"
        :pag-props="pagination"
      />
    </template>

  </v-data-table>

</template>

<script>
import { VDataTable } from "vuetify/lib/components";
import { mdiSort, mdiSortAscending, mdiSortDescending, mdiFilter, mdiChevronDown } from '@mdi/js';
import BaseDataTableHeader from "./BaseDataTableHeader.vue";
import BasePagination from "./BasePagination.vue";

export default {
  name: "BaseDataTable",
  components: {
    "v-data-table" : VDataTable,
    BaseDataTableHeader, BasePagination
  },
  data() {
    return {
      icons: { mdiSort, mdiSortAscending, mdiSortDescending, mdiFilter, mdiChevronDown },
      showFilter: false,
      page: 1,
      itemsPerPage: 10
    }
  },
  props: {
    headersProp : {
      type: Array[Object],
      required: true
    },
    itemsProp: {
      type: Array[Object],
      required: true
    },
    optionsProp: {
      type: Array[Object]
    },
    showPaginationProp: {
      type: Boolean,
      default: false
    },
    clickRowProp: {
      type: Boolean,
      default: false
    },
    searchProp: {
      type: String,
      default: ""
    },
    fixedHeaderProp: {
      type: Boolean,
      default: false
    },
    heightProp: {
      type: String
    },
    customSortProp: {
      type: Function
    }
  },
  computed: {
    headers() {
      return this.headersProp.map((item) => {
        return {
          text: item.text,
          value: item.value,
          width: item.width,
          sortable: item.sortable ? true : null,
          sort: item.sort,
          filterable: item.filterable ? true : null,
          hidden: item.hidden,
          thClass: item.thClass,
          tdClass: item.tdClass,
          subHeaders: item.subHeaders,
          align: item.align
        }
      });
    },
    headersOneLevel() {
      let sub = [];
      this.headersProp.forEach((h) => {
        if (h.subHeaders) {
          h.subHeaders.forEach((sh) => {sub.push(sh);})
        }
      });

      if (sub.length === 0) {
        return this.headers
      }
      return sub;
    },
    items() {
      return this.itemsProp;
    },
    options() {
      return this.optionsProp;
    },
    isClickableRow() {
      return this.clickRowProp;
    }
  },
  methods: {
    handlerClick(item) {
      if (item) {
        this.$emit("clickRow", item)
      }
    },
    pageChanged(data) {
      this.page = data.currentPage;
    },
    perPageChanged(data) {
      this.itemsPerPage = +data.currentPerPage;
    },
    tdClass(column) {
      return column.tdClass;
    }
  }
}
</script>

<style scoped lang="less">
@import "./css/colors";
@import "./css/base";

.v-icon {
  width: 20px;
  height: 20px;
}

.v-data-table > .v-data-table__wrapper > table {
  table-layout: fixed;

  & > tbody {
    & > tr {
      background-color: @white;
      font-weight: 500;
      font-size: 14px;
      line-height: 20px;
      color: @black;

      &:hover {
        background: transparent !important;
      }

      &:nth-child(even),
      &:nth-child(even):hover {
        background: linear-gradient(0deg, rgba(0, 66, 237, 0.025), rgba(0, 66, 237, 0.025)), @white !important;
      }

      & > td {
        overflow: hidden;
        height: 40px;
        border-left: 1px solid @transparentDay10 !important;
        border-right: 1px solid @transparentDay10 !important;
      }

      &:not(:last-child) > td {
        border-bottom: none !important;
      }

      &:last-child > td {
        border-bottom: 1px solid @transparentDay10 !important;
      }

    }
  }

}

</style>