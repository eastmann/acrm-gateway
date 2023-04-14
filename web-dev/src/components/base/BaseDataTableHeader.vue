<template>
  <thead class="v-data-table-header">
    <tr>
      <th v-if="isExpandedProp" class="th-expanded"></th>
      <template v-for="h in headers">
        <th v-if="!h.hidden" :key="h.value"
            :class="thClass(h)"
            :colspan="h.colspan"
            :style="{'text-align' : h.align, 'width': h.width}"
        >
          <span v-html="h.text"></span>
          <v-btn v-if="h.sortable" icon text
                 :ripple="false"
                 @click="sort(h.value)"
                 class="v-data-table-header__icon"
          >
            <v-icon>{{ sortIcon(h.value) }}</v-icon>
          </v-btn>
          <!--        <v-icon-->
          <!--            v-if="h.filterable"-->
          <!--            @click="showFilter = !showFilter"-->
          <!--            class="v-data-table-header__icon"-->
          <!--        >{{ icons.mdiFilter }}</v-icon>-->
        </th>
      </template>
    </tr>
    <tr v-if="subHeaders.length">
      <th v-if="isExpandedProp" class="th-expanded"></th>
      <template v-for="h in subHeaders">
        <th v-if="!h.hidden" :key="h.value"
            :class="thClass(h)"
            :style="{'width': h.width, 'text-align': h.align}"
        >
          <span v-html="h.text"></span>
          <v-btn v-if="h.sortable" icon text
                 :ripple="false"
                 @click="sort(h.value)"
                 class="v-data-table-header__icon"
          >
            <v-icon>{{ sortIcon(h.value) }}</v-icon>
          </v-btn>
          <!--        <v-icon-->
          <!--            v-if="h.filterable"-->
          <!--            @click="showFilter = !showFilter"-->
          <!--            class="v-data-table-header__icon"-->
          <!--        >{{ icons.mdiFilter }}</v-icon>-->
        </th>
      </template>
    </tr>

  </thead>

<!--  <tr v-if="showFilter">-->
<!--    <td v-for="h in headers" :key="h.value">-->
<!--      <v-text-field v-if="h.filterable"-->
<!--                    type="text" v-model="h.value"-->
<!--      ></v-text-field>-->
<!--    </td>-->
<!--  </tr>-->
</template>

<script>
import { mdiChevronDown, mdiChevronUp, mdiUnfoldMoreHorizontal, mdiFilter } from "@mdi/js";
import {VBtn, VIcon} from "vuetify/lib/components";

export default {
  name: "BaseDataTableHeader",
  components: {
    "v-icon" : VIcon,
    "v-btn" : VBtn
  },
  data() {
    return {
      icons: { mdiChevronDown, mdiChevronUp, mdiUnfoldMoreHorizontal, mdiFilter },
      hideFooter: true,
      showFilter: false,
      search: ""
    }
  },
  props: {
    headersProp: {
      type: Array[Object],
      required: true
    },
    optionsProp: {
      type: Array[Object]
    },
    isExpandedProp: {
      type: Boolean,
      default: false
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
          colspan: item.subHeaders ? item.subHeaders.length : 1,
          align: item.align
        }
      });
    },
    subHeaders() {
      let sub = [];
      this.headersProp.forEach((h) => {
        if (h.subHeaders) {
          h.subHeaders.forEach((sh) => {sub.push(sh);})
        }
      });
      return sub;
    },
    options() {
      return this.optionsProp;
    }
  },
  methods: {
    sortIcon(column) {
      if (this.options.sortBy[0] === column) {
        return this.options.sortDesc[0] ? this.icons.mdiChevronDown : this.icons.mdiChevronUp;
      }

      return this.icons.mdiUnfoldMoreHorizontal
    },
    sort(column) {
      if (this.options.sortBy[0] === column) {
        this.$set(this.options.sortDesc, 0, !this.options.sortDesc[0]);
      } else {
        this.$set(this.options.sortBy, 0, column);
        this.$set(this.options.sortDesc, 0, false);
      }
    },
    thClass(column) {
      return "column " + column.thClass ? column.thClass : "";
    }
  }
}
</script>

<style scoped lang="less">
@import "./css/base";
@import "./css/colors";

.v-data-table > .v-data-table__wrapper > table > thead {
  & > tr {
    background-color: @gray5;

    &:last-child > th {
      border-bottom: 1px solid @transparentDay10 !important;
    }

    & > th {
      height: 40px !important;
      padding: 0 12px;
      color: @transparentDay50 !important;
      font-size: 14px;
      font-weight: 500;
      line-height: 20px;
      border: 1px solid @transparentDay10 !important;

      &:hover .v-btn--icon::before {
        background-color: transparent !important;
      }

      &.th-expanded {
        width: 40px !important;
      }
    }
  }
}

.v-data-table.v-data-table--fixed-header > .v-data-table__wrapper table thead > tr > th {
  height: 40px;
  padding: 0 12px;
  background-color: @gray5;
  box-shadow: none;
  -webkit-box-shadow: none;

  color: @transparentDay50;
  font-size: 14px;
  font-weight: 500;
  line-height: 20px;
}

.v-data-table--fixed-header > .v-data-table__wrapper > table > thead > tr:nth-child(2) > th {
  top: 40px;
}

</style>