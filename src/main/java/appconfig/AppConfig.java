package appconfig;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:project.properties")
public interface AppConfig extends Config {

        @Key("request_to_smev_tor_templates_dev2")
        String requestToSmevTorTemplatesDev2();

        @Key("request_to_smev_tor_templates_uat")
        String requestToSmevTorTemplatesUat();

        @Key("request_to_smev_tor_templates_dev")
        String requestToSmevTorTemplatesDev();

        @Key("smev_request_broadcast_uat")
        String smevRequestBroadcastUat();

        @Key("smev_request_broadcast_dev2")
        String smevRequestBroadcastDev2();

        @Key("do_knd_base_url_uat")
        String doKndBaseUrlUat();

        @Key("do_knd_api_base_url_uat")
        String doKndApiBaseUrlUat();

        @Key("do_knd_api_base_url_dev2")
        String doKndApiBaseUrlDev2();

        @Key("do_knd_base_url_dev")
        String doKndBaseUrlDev();

        @Key("do_knd_base_url_dev2")
        String doKndBaseUrlDev2();

        // Appeal Form URLs
        @Key("do_knd_appeal_form_url_uat")
        String doKndAppealFormUrlUat();

        @Key("do_knd_appeal_form_url_dev2")
        String doKndAppealFormUrlDev2();

        // Repeated Appeal Form URL
        @Key("knd_repeated_appeal_form_url_uat")
        String doKndRepeatedAppealFormUrlUat();

        // Profile URLs
        @Key("pgu_org_profile_knd_url_uat")
        String pguOrgProfileKndUrlUat();

        @Key("pgu_org_profile_knd_url_dev2")
        String pguOrgProfileKndUrlDev2();

        @Key("pgu_org_profile_inspect_url_uat")
        String pguOrgProfileInspectUrlUat();

        @Key("pgu_inspect_url_uat")
        String pguInspectUrlUat();

        @Key("pgu_information_checks_url_uat")
        String pguInformationChecksUrlUat();

        @Key("pgu_checks_form_url_uat")
        String pguChecksFormUrlUat();

        // Control Objects and Activities
        @Key("pgu_control_objects_url_uat")
        String pguControlObjectsUrlUat();

        @Key("pgu_org_activity_url_uat")
        String pguOrgActivityUrlUat();

        // SMEV URLs
        @Key("smev_adapter_url_uat")
        String smevAdapterUrlUat();

        @Key("smev_adapter_url_dev2")
        String smevAdapterUrlDev2();

        @Key("smev_adapter_response_to_pgu_url_uat")
        String smevAdapterResponseToPguUrlUat();

        @Key("smev_adapter_response_to_pgu_url_dev2")
        String smevAdapterResponseToPguUrlDev2();

        // TOR Templates SMEV URLs
        @Key("tor_templates_smev_url_uat")
        String torTemplatesSmevUrlUat();

        @Key("tor_templates_smev_url_dev2")
        String torTemplatesSmevUrlDev2();

        // Kuber Discover URLs
        @Key("kuber_discover_url_uat")
        String kuberDiscoverUrlUat();

        @Key("kuber_discover_url_dev2")
        String kuberDiscoverUrlDev2();

        // Appeals All URLs
        @Key("knd_appeals_all_url_uat")
        String kndAppealsAllUrlUat();

        @Key("knd_appeals_all_url_dev2")
        String kndAppealsAllUrlDev2();

        // Second Appeal URL
        @Key("knd_second_appeal_url_uat")
        String kndSecondAppealUrlUat();

        // User Credentials
        @Key("user_login_bespalov")
        String userLoginBespalov();

        @Key("user_password_bespalov")
        String userPasswordBespalov();

        @Key("user_login_emelyanov")
        String userLoginEmelyanov();

        // GEPS IDs
        @Key("geps_id_bespalov_ip")
        String gepsIdBespalovIp();

        @Key("geps_id_bespalov_ul")
        String gepsIdBespalovUl();

        @Key("geps_id_bespalov_fl")
        String gepsIdBespalovFl();

        @Key("geps_id_ivanov_ul")
        String gepsIdIvanovUl();

        @Key("geps_id_ivanov_fl")
        String gepsIdIvanovFl();

        // Subject URLs for UAT Environment
        @Key("uat_check_procedure_violation_id_1")
        String uatCheckProcedureViolationId1();

        @Key("uat_disagree_with_act_violations_id_2")
        String uatDisagreeWithActViolationsId2();

        @Key("uat_order_of_check_assignment_violation_id_3")
        String uatOrderOfCheckAssignmentViolationId3();

        @Key("uat_disagree_with_actions_of_official_id_4")
        String uatDisagreeWithActionsOfOfficialId4();

        @Key("uat_disagree_with_measures_id_5")
        String uatDisagreeWithMeasuresId5();

        @Key("uat_complaint_for_breach_of_moratorium_id_10")
        String uatComplaintForBreachOfMoratoriumId10();

        @Key("uat_extension_of_prescription_execution_period_id_11")
        String uatExtensionOfPrescriptionExecutionPeriodId11();

        @Key("uat_disagree_with_risk_category_assignment_id_12")
        String uatDisagreeWithRiskCategoryAssignmentId12();

        @Key("uat_disagree_with_control_organ_decision_mobilization_id_13")
        String uatDisagreeWithControlOrganDecisionMobilizationId13();

        @Key("uat_disagree_with_profvisit_prescription_id_14")
        String uatDisagreeWithProfvisitPrescriptionId14();

        @Key("uat_objection_caution_id_15")
        String uatObjectionCautionId15();

        // Subject URLs for DEV2 Environment
        @Key("dev2_check_procedure_violation_id_1")
        String dev2CheckProcedureViolationId1();

        @Key("dev2_disagree_with_act_violations_id_2")
        String dev2DisagreeWithActViolationsId2();

        @Key("dev2_order_of_check_assignment_violation_id_3")
        String dev2OrderOfCheckAssignmentViolationId3();

        @Key("dev2_disagree_with_actions_of_official_id_4")
        String dev2DisagreeWithActionsOfOfficialId4();

        @Key("dev2_disagree_with_measures_id_5")
        String dev2DisagreeWithMeasuresId5();

        @Key("dev2_complaint_for_breach_of_moratorium_id_10")
        String dev2ComplaintForBreachOfMoratoriumId10();

        @Key("dev2_extension_of_prescription_execution_period_id_11")
        String dev2ExtensionOfPrescriptionExecutionPeriodId11();

        @Key("dev2_disagree_with_risk_category_assignment_id_12")
        String dev2DisagreeWithRiskCategoryAssignmentId12();

        @Key("dev2_disagree_with_control_organ_decision_mobilization_id_13")
        String dev2DisagreeWithControlOrganDecisionMobilizationId13();

        @Key("dev2_disagree_with_profvisit_prescription_id_14")
        String dev2DisagreeWithProfvisitPrescriptionId14();

        @Key("dev2_objection_caution_id_15")
        String dev2ObjectionCautionId15();
    }


