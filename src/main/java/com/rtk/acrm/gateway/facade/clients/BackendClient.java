package com.rtk.acrm.gateway.facade.clients;

import com.rtk.acrm.gateway.facade.BaseRestClient;
import com.rtk.acrm.gateway.facade.dto.*;
import com.rtk.acrm.gateway.facade.dto.holding_detail.*;
import com.rtk.acrm.gateway.session.HttpSession;
import com.rtk.acrm.gateway.utils.NumberUtils;
import com.rtk.acrm.gateway.utils.StringUtils;
import com.rtk.acrm.gateway.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class BackendClient extends BaseRestClient {

	private Logger logger = LoggerFactory.getLogger(BackendClient.class);

	@Value("${backend.read-time-out}")
	private Integer READ_TIME_OUT;

	@Value("${backend.connection-time-out}")
	private Integer CONNECTION_TIME_OUT;

	@Value("${zuul.routes.backend.url}")
	private String BACKEND;

	@Value("${backend.holdings}")
	private String GET_HOLDINGS;

	@Value("${backend.holding-detail}")
	private String GET_HOLDING_DETAIL;

	private String GET_HOLDINGS_URL;
	private String GET_HOLDING_DETAIL_URL;

	protected final HttpSession session;

	@Autowired
	public BackendClient(RestTemplate restTemplate, HttpSession session) {
		super(restTemplate);
		this.session = session;
	}

	@PostConstruct
	public void init() {
		GET_HOLDINGS_URL = UrlUtils.joinUrl(BACKEND, GET_HOLDINGS);
		GET_HOLDING_DETAIL_URL = UrlUtils.joinUrl(BACKEND, GET_HOLDING_DETAIL);
	}

	public HoldingsDto getHoldings(){
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("login", getLogin());
		return getRequest(GET_HOLDINGS_URL, HoldingsDto.class, urlParams, READ_TIME_OUT, CONNECTION_TIME_OUT);
	}

	public HoldingDetailDto getHoldingDetail(String hid, String id, String dzoId){
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("hid", hid);
		urlParams.put("id", id);
		urlParams.put("dzo-id", dzoId);
		urlParams.put("login", getLogin());
		return getRequest(GET_HOLDING_DETAIL_URL, HoldingDetailDto.class, urlParams, READ_TIME_OUT, CONNECTION_TIME_OUT);
	}

	private Boolean isEmpty(String val){
		return (val == null) || ("".equals(val));
	}

	private List<KamInfoFrontDto> transformKamInfo(List<KamLprInfo> kamLprInfos){
		List<KamInfoFrontDto> kamInfoFrontDtos = new ArrayList<>();

		for (KamLprInfo kamLprInfo : kamLprInfos){
			if (	!isEmpty(kamLprInfo.getKamO()) ||
					!isEmpty(kamLprInfo.getKamOContact()) ||
					!isEmpty(kamLprInfo.getKamOEmail())	){
				kamInfoFrontDtos.add(
						new KamInfoFrontDto("КАМ", kamLprInfo.getKamO(), kamLprInfo.getKamOContact(),
								kamLprInfo.getKamOEmail(), kamLprInfo.getDzoId())
				);
			}

			if (	!isEmpty(kamLprInfo.getDirO()) ||
					!isEmpty(kamLprInfo.getDirOContact()) ||
					!isEmpty(kamLprInfo.getDirOEmail())	){
				kamInfoFrontDtos.add(
						new KamInfoFrontDto("Директор", kamLprInfo.getDirO(), kamLprInfo.getDirOContact(),
								kamLprInfo.getDirOEmail(), kamLprInfo.getDzoId())
				);
			}

			if (	!isEmpty(kamLprInfo.getKamH()) ||
					!isEmpty(kamLprInfo.getKamHContact()) ||
					!isEmpty(kamLprInfo.getKamHEmail())	){
				kamInfoFrontDtos.add(
						new KamInfoFrontDto("Ведущий КАМ", kamLprInfo.getKamH(), kamLprInfo.getKamHContact(),
								kamLprInfo.getKamHEmail(), kamLprInfo.getDzoId())
				);
			}
		}

		return kamInfoFrontDtos;
	}

	private HoldingMainFrontDto createMainFrontDto(HoldingMain holdingMain){
		HoldingMainFrontDto mainFrontDto = new HoldingMainFrontDto();
		mainFrontDto.setId(holdingMain.getId());
		mainFrontDto.setInn(holdingMain.getInn());
		mainFrontDto.setDzoId(holdingMain.getDzoId());
		mainFrontDto.setKpp(holdingMain.getKpp());

		return mainFrontDto;
	}

	private List<HoldingMainFrontDto> transformHoldingMain(List<HoldingMain> holdingMains){
		List<HoldingMainFrontDto> holdingMainFrontDtos = new ArrayList<>();

		for (HoldingMain holdingMain : holdingMains){

			HoldingMainFrontDto vir = createMainFrontDto(holdingMain);
			vir.setName("Выручка, млн. ₽");
			vir.setYear2020(StringUtils.formatBillion(holdingMain.getVirDzo2020()));
			vir.setYear2021(StringUtils.formatBillion(holdingMain.getVirDzo2021()));
			vir.setYear2022(StringUtils.formatBillion(holdingMain.getVirDzo2022()));
			vir.setYear2023("");
			holdingMainFrontDtos.add(vir);

			HoldingMainFrontDto budget = createMainFrontDto(holdingMain);
			budget.setName("Бюджет, млн. ₽");
			budget.setYear2020("");
			budget.setYear2021("");
			budget.setYear2022(StringUtils.formatBillion(holdingMain.getBudgetDzo2022()));
			budget.setYear2023(StringUtils.formatBillion(holdingMain.getBudgetDzo2022()));
			holdingMainFrontDtos.add(budget);

			HoldingMainFrontDto plan = createMainFrontDto(holdingMain);
			plan.setName("План, млн. ₽");
			plan.setYear2020(StringUtils.formatBillion(holdingMain.getPribDzo2020()));
			plan.setYear2021(StringUtils.formatBillion(holdingMain.getPribDzo2021()));
			plan.setYear2022(StringUtils.formatBillion(holdingMain.getPribDzo2022()));
			plan.setYear2023("");
			holdingMainFrontDtos.add(plan);

			HoldingMainFrontDto fact = createMainFrontDto(holdingMain);
			fact.setName("Факт по маржинальности, %");
			fact.setYear2020("");
			fact.setYear2021(StringUtils.formatBillion(holdingMain.getMarginFact2021()));
			fact.setYear2022(StringUtils.formatBillion(holdingMain.getMarginFact2022()));
			fact.setYear2023("");
			holdingMainFrontDtos.add(fact);
		}

		return holdingMainFrontDtos;
	}

	public List<HoldingMain> getHoldingMainsTotal(List<HoldingMain> holdingMains) {
		if (holdingMains == null){
			return null;
		}
		Double virDzo2020 = 0d;
		Double virDzo2021 = 0d;
		Double virDzo2022 = 0d;
		Double budgetDzo2022 = 0d;
		Double budgetDzo2023 = 0d;
		Double marginFact2021 = 0d;
		Double marginFact2022 = 0d;

		for (HoldingMain holdingMain : holdingMains){
			virDzo2020 += getDouble(holdingMain.getVirDzo2020());
			virDzo2021 += getDouble(holdingMain.getVirDzo2021());
			virDzo2022 += getDouble(holdingMain.getVirDzo2022());
			budgetDzo2022 += getDouble(holdingMain.getBudgetDzo2022());
			budgetDzo2023 += getDouble(holdingMain.getBudgetDzo2023());
			marginFact2021 += getDouble(holdingMain.getMarginFact2021());
			marginFact2022 += getDouble(holdingMain.getMarginFact2022());
		}
		HoldingMain holdingMain = new HoldingMain();
		holdingMain.setVirDzo2020(virDzo2020);
		holdingMain.setVirDzo2021(virDzo2021);
		holdingMain.setVirDzo2022(virDzo2022);
		holdingMain.setBudgetDzo2022(budgetDzo2022);
		holdingMain.setBudgetDzo2023(budgetDzo2023);
		holdingMain.setMarginFact2021(marginFact2021);
		holdingMain.setMarginFact2022(marginFact2022);

		return Collections.singletonList(holdingMain);
	}

	private List<HoldingMainFrontDto> transformHoldingMainWallet(List<HoldingMain> holdingMains){
		List<HoldingMainFrontDto> holdingMainFrontDtos = new ArrayList<>();

		for (HoldingMain holdingMain : holdingMains){

			HoldingMainFrontDto iktGeneralBud = createMainFrontDto(holdingMain);
			iktGeneralBud.setName("Общий ИКТ бюджет, млн. ₽");
			iktGeneralBud.setYear2023(StringUtils.formatBillion(holdingMain.getIktGeneralBud()));
			iktGeneralBud.setYear2024("");
			holdingMainFrontDtos.add(iktGeneralBud);

			HoldingMainFrontDto iktAddressBud = createMainFrontDto(holdingMain);
			iktAddressBud.setName("Адресуемый, доступный для РТК ИКТ бюджет, млн. ₽");
			iktAddressBud.setYear2023(StringUtils.formatBillion(holdingMain.getIktAddressBud()));
			iktAddressBud.setYear2024("");
			holdingMainFrontDtos.add(iktAddressBud);

			HoldingMainFrontDto iktNonaddressBud = createMainFrontDto(holdingMain);
			iktNonaddressBud.setName("Неадресуемый, недоступный для РТК ИКТ бюджет, млн. ₽");
			iktNonaddressBud.setYear2023(StringUtils.formatBillion(holdingMain.getIktNonaddressBud()));
			iktNonaddressBud.setYear2024("");
			holdingMainFrontDtos.add(iktNonaddressBud);

			HoldingMainFrontDto iktBudComment = createMainFrontDto(holdingMain);
			iktBudComment.setName("Комментарий к бюджету/проектам");
			iktBudComment.setYear2023(holdingMain.getIktBudComment());
			iktBudComment.setYear2024("");
			holdingMainFrontDtos.add(iktBudComment);
		}

		return holdingMainFrontDtos;
	}

	String getLogin(){
		AuthorizationInfo authInfo = session.getInfo();
		String login = "";
		if (authInfo != null){
			login = authInfo.getLogin();
		}

		return login;
	}

	public List<ProductWithMarginDto> sortProducts(List<ProductWithMarginDto> products) {
		products.sort(new Comparator<ProductWithMarginDto>() {
			@Override
			public int compare(ProductWithMarginDto b1, ProductWithMarginDto b2) {
				if (b1 == null){
					return 1;
				}

				if (b2 == null){
					return -1;
				}

				if (b1.getProductName().equals("Прочие")){
					return 1;
				}
				if (b2.getProductName().equals("Прочие")){
					return -1;
				}

				Double val1 = NumberUtils.parseDouble(b1.getVyruchka2022());
				Double val2 = NumberUtils.parseDouble(b2.getVyruchka2022());
				if (val1 == null){
					return 1;
				}
				if (val2 == null){
					return -1;
				}

				return -1 * val1.compareTo(val2);
			}
		});
		return products;
	}

	public HoldingDetailDto getHoldingDetailTransform(String hid, String id, String dzoId){

		HoldingDetailDto holdingDetail = getHoldingDetail(hid, id, dzoId);
		//holdingDetail.setKamInfoFront(transformKamInfo(holdingDetail.getKamLprInfo()));
		holdingDetail.setHoldingMainsFront(transformHoldingMain(holdingDetail.getHoldingMains()));
		holdingDetail.setHoldingMainsTotalFront(transformHoldingMain(getHoldingMainsTotal(holdingDetail.getHoldingMains())));
		holdingDetail.setHoldingMainsWallet(transformHoldingMainWallet(holdingDetail.getHoldingMains()));
		holdingDetail.setProductMargin(sortProducts(holdingDetail.getProductMargin()));
		return holdingDetail;
	}

	public HoldingsDto getHoldingsAndTransform(){
		HoldingsDto holdings = getHoldings();
		if ((holdings == null) || (holdings.getHoldings() == null)){
			return null;
		}

		for (HoldingsStartPage holding : holdings.getHoldings()){
			holding.setAllVir2020format(StringUtils.formatBillion(holding.getAllVir2020()));
			holding.setAllVir2021format(StringUtils.formatBillion(holding.getAllVir2021()));
		}

		return holdings;
	}

	private Double getDouble(Double val){
		if (val == null){
			return 0d;
		}

		return val;
	}
}
