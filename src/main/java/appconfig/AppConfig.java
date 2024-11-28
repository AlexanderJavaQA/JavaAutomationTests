package appconfig;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:project.properties")
public interface AppConfig extends Config {

        @Key("smev_tor_request")
        String smevTorRequest();

        @Key("smev_status_appeal_request")
        String smevStatusAppealRequest();

        @Key("smev_broadcast_request")
        String smevBroadcastRequest();

        @Key("smev_broadcast_request_dev2")
        String smevBroadcastRequestDev2();

        @Key("appeals_page")
        String appealsPage();

        @Key("esia_auth")
        String esiaAuth();

        @Key("api_base_url")
        String apiBaseUrl();


        // Repeated Appeal Form URL
        @Key("repeated_appeal_page")
        String repeatedAppealPage();

        // Profile URLs
        @Key("org_profile_page")
        String orgProfilePage();

        @Key("inspection_page")
        String inspectionPage();

        @Key("control_activities")
        String controlActivitiesPage();



        // Control Objects and Activities
        @Key("control_objects_page")
        String controlObjectsPage();

        @Key("business_activity_page")
        String businessActivityPage();

        // Kuber Discover URLs
        @Key("kibana_discover_page")
        String kibanaDiscoverPage();

        // Appeals All URLs
        @Key("my_appeals_page")
        String myAppealsPage();


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


