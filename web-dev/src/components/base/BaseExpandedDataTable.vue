<template>
  <v-data-table
      :headers="headersOneLevel"
      :items="itemsProp"
      :options="options"
      :search="searchProp"
      hide-default-header
      hide-default-footer
      :show-expand="showExpand"
      :item-key="itemKey"
      @item-expanded="loadExpandedData"
      :page="page"
      :items-per-page="itemsPerPage"
      :custom-sort="customSortProp"
      no-results-text="Нет данных"
      no-data-text="Нет данных"
  >

    <template v-slot:header>
      <base-data-table-header
          :headers-prop="headers"
          :options-prop="options"
          is-expanded-prop
      />
      <!--      <tr v-if="showFilter">-->
      <!--        <td v-for="h in headers" :key="h.value">-->
      <!--          <v-text-field  v-if="h.filterable"-->
      <!--                         type="text" v-model="h.value"-->
      <!--          ></v-text-field>-->
      <!--        </td>-->
      <!--      </tr>-->
    </template>

    <template v-slot:item="{ item : item, expand, isExpanded }">
      <tr @click="handlerClick(item)"
          :class="{'clickable' : isClickableRow}">
        <td v-if="showExpand" class="td-expanded">
          <v-btn icon text
                 :ripple="false"
                 @click.stop="expand(!isExpanded)"
                 class="v-data-table__expand-icon"
                 :class="{'v-data-table__expand-icon--active' : isExpanded}"
          >
            <v-icon>{{ icons.mdiChevronRight }}</v-icon>
          </v-btn>
        </td>
        <template v-for="header in headersOneLevel">
          <td v-if="!header.hidden" :key="header.value"
              :class="tdClass(header)"
              v-html="item[header.value]"
          ></td>
        </template>
      </tr>
    </template>

    <template v-slot:expanded-item="{ headers : headers, item : item }">
      <tr>
        <td :colspan="headersOneLevel.length + 1"
            class="v-data-table__expanded__content">
          <base-data-table
              :headers-prop="expanded[item[itemKey]].headers"
              :items-prop="expanded[item[itemKey]].items"
              :options-prop="options"
              height-prop="240"
              :fixed-header-prop="true"
              :custom-sort-prop="expanded.customSort"
          />
        </td>
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
import {VDataTable, VBtn, VIcon} from "vuetify/lib/components";
import {mdiSort, mdiSortAscending, mdiSortDescending, mdiFilter, mdiChevronDown, mdiChevronRight} from '@mdi/js';
import BaseDataTable from "@/components/base/BaseDataTable.vue";
import BaseDataTableHeader from "@/components/base/BaseDataTableHeader.vue";
import BasePagination from "@/components/base/BasePagination.vue";

export default {
  name: "BaseDataExpandedTable",
  components: {
    "v-data-table": VDataTable,
    "v-btn": VBtn,
    "v-icon": VIcon,
    BaseDataTable, BaseDataTableHeader, BasePagination
  },
  data() {
    return {
      icons: {mdiSort, mdiSortAscending, mdiSortDescending, mdiFilter, mdiChevronDown, mdiChevronRight},
      showFilter: false,
      expanded: {headers: "", items: ""},
      page: 1,
      itemsPerPage: 10
    }
  },
  props: {
    headersProp: {
      type: Array[Object],
      required: true
    },
    itemsProp: {
      type: Array[Object],
      required: true
    },
    itemKeyProp: {
      type: String,
      default: "name"
    },
    optionsProp: {
      type: Array[Object]
    },
    showExpandProp: {
      type: Boolean,
      default: false,
      required: true
    },
    expandedProp: {
      type: Array[Object],
      default() {
        return [{ headers: "", items: "" }]
      }
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
    options() {
      return this.optionsProp;
    },
    showExpand() {
      return this.showExpandProp;
    },
    itemKey() {
      return this.itemKeyProp;
    },
    isClickableRow() {
      return this.clickRowProp;
    }
  },
  methods: {
    loadExpandedData(data) {
      if (data.value) {
        this.$emit("itemExpanded", data.item);
        this.expanded = this.expandedProp;
      }
    },
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
      return column.tdClass ? column.tdClass : "";
    }
  }
}
</script>

<style scoped lang="less">
@import "./css/base";
@import "./css/colors";

.v-data-table > .v-data-table__wrapper > table {
  table-layout: fixed;
  border-collapse: collapse;

  & > tbody {
    & > tr {
      background-color: @white;
      font-weight: 500;
      font-size: 14px;
      line-height: 20px;
      color: @black;

      .v-data-table__expanded__content {
        padding: 0;
        border: 1px solid @transparentDay10 !important;
      }

      &:hover {
        background: transparent !important;
      }

      &:nth-child(even),
      &:nth-child(even):hover {
        background: linear-gradient(0deg, rgba(0, 66, 237, 0.025), rgba(0, 66, 237, 0.025)), @white !important;
      }

      &:last-child > td {
        border-bottom: 1px solid @transparentDay10 !important;
      }

      & > td {
        overflow: hidden;
        height: 40px;
        border-bottom: none !important;
        border-left: 1px solid @transparentDay10 !important;
        border-right: 1px solid @transparentDay10 !important;

        .v-data-table__expand-icon::before {
          background-color: transparent !important;
        }

        &.td-expanded {
          width: 40px !important;
          padding: 0 2px;
        }
      }
    }
  }
}

.v-data-table__expand-icon--active {
  -webkit-transform:rotate(90deg);
  transform:rotate(90deg)
}

</style>