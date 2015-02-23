//package com.anagaf.freqhelper.model.ranges;
//
//import com.anagaf.freqhelper.R;
//import com.anagaf.freqhelper.model.keys.DcsCode;
//import com.anagaf.freqhelper.model.keys.Frequency;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///*
//023	047 i
//025	244 i
//026	464 i
//031	627 i
//032	051 i
//036	172 i
//043	445 i
//047	023 i
//051	032 i
//053	452 i
//054	413 i
//065	271 i
//071	306 i
//072	245 i
//073	506 i
//074	174 i
//114	712 i
//115	152 i
//116	754 i
//122	225 i
//125	365 i
//131	364 i
//132	546 i
//134	223 i
//143	412 i
//145	274 i
//152	115 i
//155	731 i
//156	265 i
//162	503 i
//165	251 i
//172	036 i
//174	074 i
//205	263 i
//212	356 i
//223	134 i
//225	122 i
//226	411 i
//243	351 i
//244	025 i
//245	072 i
//246	523 i
//251	165 i
//252	462 i
//255	446 i
//261	732 i
//263	205 i
//265	156 i
//266	454 i
//271	065 i
//274	145 i
//306	071 i
//311	664 i
//315	423 i
//325	526 i
//331	465 i
//332	455 i
//343	532 i
//346	612 i
//351	243 i
//356	212 i
//364	131 i
//365	125 i
//371	734 i
//411	226 i
//412	143 i
//413	054 i
//423	315 i
//431	723 i
//432	516 i
//445	043 i
//446	255 i
//452	053 i
//454	266 i
//455	332 i
//462	252 i
//464	026 i
//465	331 i
//466	662 i
//503	162 i
//506	073 i
//516	432 i
//523	246 i
//526	325 i
//532	343 i
//546	132 i
//565	703 i
//606	631 i
//612	346 i
//624	632 i
//627	031 i
//631	606 i
//632	624 i
//654	743 i
//662	466 i
//664	311 i
//703	565 i
//712	114 i
//723	431 i
//731	155 i
//732	261 i
//734	371 i
//743	654 i
//754	116 i
//*/
//public class DirectDcs extends StaticRange {
//
//    private static final List<Frequency> sCodes;
//    private static final List<Frequency> sInverseCodes;
//    static {
//        List<Frequency> codes = new ArrayList<>();
//        codes.add(new DcsCode(23));
//        codes.add(new DcsCode(25));
//        codes.add(new DcsCode(26));
//        codes.add(new DcsCode(31));
//        codes.add(new DcsCode(32));
//        codes.add(new DcsCode(36));
//        codes.add(new DcsCode(43));
//        codes.add(new DcsCode(47));
//        codes.add(new DcsCode(51));
//        codes.add(new DcsCode(53));
//        codes.add(new DcsCode(54));
//        codes.add(new DcsCode(65));
//        codes.add(new DcsCode(71));
//        codes.add(new DcsCode(72));
//        codes.add(new DcsCode(73));
//        codes.add(new DcsCode(74));
//        codes.add(new DcsCode(114));
//        codes.add(new DcsCode(115));
//        codes.add(new DcsCode(116));
//        codes.add(new DcsCode(122));
//        codes.add(new DcsCode(125));
//        codes.add(new DcsCode(131));
//        codes.add(new DcsCode(132));
//        codes.add(new DcsCode(134));
//        codes.add(new DcsCode(143));
//        codes.add(new DcsCode(145));
//        codes.add(new DcsCode(152));
//        codes.add(new DcsCode(155));
//        codes.add(new DcsCode(156));
//        codes.add(new DcsCode(162));
//        codes.add(new DcsCode(165));
//        codes.add(new DcsCode(172));
//        codes.add(new DcsCode(174));
//        codes.add(new DcsCode(205));
//        codes.add(new DcsCode(212));
//        codes.add(new DcsCode(223));
//        codes.add(new DcsCode(225));
//        codes.add(new DcsCode(226));
//        codes.add(new DcsCode(243));
//        codes.add(new DcsCode(244));
//        codes.add(new DcsCode(245));
//        codes.add(new DcsCode(246));
//        codes.add(new DcsCode(251));
//        codes.add(new DcsCode(252));
//        codes.add(new DcsCode(255));
//        codes.add(new DcsCode(261));
//        codes.add(new DcsCode(263));
//        codes.add(new DcsCode(265));
//        codes.add(new DcsCode(266));
//        codes.add(new DcsCode(271));
//        codes.add(new DcsCode(274));
//        codes.add(new DcsCode(306));
//        codes.add(new DcsCode(311));
//        codes.add(new DcsCode(315));
//        codes.add(new DcsCode(325));
//        codes.add(new DcsCode(331));
//        codes.add(new DcsCode(332));
//        codes.add(new DcsCode(343));
//        codes.add(new DcsCode(346));
//        codes.add(new DcsCode(351));
//        codes.add(new DcsCode(356));
//        codes.add(new DcsCode(364));
//        codes.add(new DcsCode(365));
//        codes.add(new DcsCode(371));
//        codes.add(new DcsCode(411));
//        codes.add(new DcsCode(412));
//        codes.add(new DcsCode(413));
//        codes.add(new DcsCode(423));
//        codes.add(new DcsCode(431));
//        codes.add(new DcsCode(432));
//        codes.add(new DcsCode(445));
//        codes.add(new DcsCode(446));
//        codes.add(new DcsCode(452));
//        codes.add(new DcsCode(454));
//        codes.add(new DcsCode(455));
//        codes.add(new DcsCode(462));
//        codes.add(new DcsCode(464));
//        codes.add(new DcsCode(465));
//        codes.add(new DcsCode(466));
//        codes.add(new DcsCode(503));
//        codes.add(new DcsCode(506));
//        codes.add(new DcsCode(516));
//        codes.add(new DcsCode(523));
//        codes.add(new DcsCode(526));
//        codes.add(new DcsCode(532));
//        codes.add(new DcsCode(546));
//        codes.add(new DcsCode(565));
//        codes.add(new DcsCode(606));
//        codes.add(new DcsCode(612));
//        codes.add(new DcsCode(624));
//        codes.add(new DcsCode(627));
//        codes.add(new DcsCode(631));
//        codes.add(new DcsCode(632));
//        codes.add(new DcsCode(654));
//        codes.add(new DcsCode(662));
//        codes.add(new DcsCode(664));
//        codes.add(new DcsCode(703));
//        codes.add(new DcsCode(712));
//        codes.add(new DcsCode(723));
//        codes.add(new DcsCode(731));
//        codes.add(new DcsCode(732));
//        codes.add(new DcsCode(734));
//        codes.add(new DcsCode(743));
//        codes.add(new DcsCode(754));
//        sCodes = Collections.unmodifiableList(codes);
//
//        List<Frequency> inverseCodes = new ArrayList<>();
//        inverseCodes.add(new DcsCode(47));
//        inverseCodes.add(new DcsCode(244));
//        inverseCodes.add(new DcsCode(464));
//        inverseCodes.add(new DcsCode(627));
//        inverseCodes.add(new DcsCode(51));
//        inverseCodes.add(new DcsCode(172));
//        inverseCodes.add(new DcsCode(445));
//        inverseCodes.add(new DcsCode(23));
//        inverseCodes.add(new DcsCode(32));
//        inverseCodes.add(new DcsCode(452));
//        inverseCodes.add(new DcsCode(413));
//        inverseCodes.add(new DcsCode(271));
//        inverseCodes.add(new DcsCode(306));
//        inverseCodes.add(new DcsCode(245));
//        inverseCodes.add(new DcsCode(506));
//        inverseCodes.add(new DcsCode(174));
//        inverseCodes.add(new DcsCode(712));
//        inverseCodes.add(new DcsCode(152));
//        inverseCodes.add(new DcsCode(754));
//        inverseCodes.add(new DcsCode(225));
//        inverseCodes.add(new DcsCode(365));
//        inverseCodes.add(new DcsCode(364));
//        inverseCodes.add(new DcsCode(546));
//        inverseCodes.add(new DcsCode(223));
//        inverseCodes.add(new DcsCode(412));
//        inverseCodes.add(new DcsCode(274));
//        inverseCodes.add(new DcsCode(115));
//        inverseCodes.add(new DcsCode(731));
//        inverseCodes.add(new DcsCode(265));
//        inverseCodes.add(new DcsCode(503));
//        inverseCodes.add(new DcsCode(251));
//        inverseCodes.add(new DcsCode(36));
//        inverseCodes.add(new DcsCode(74));
//        inverseCodes.add(new DcsCode(263));
//        inverseCodes.add(new DcsCode(356));
//        inverseCodes.add(new DcsCode(134));
//        inverseCodes.add(new DcsCode(122));
//        inverseCodes.add(new DcsCode(411));
//        inverseCodes.add(new DcsCode(351));
//        inverseCodes.add(new DcsCode(25));
//        inverseCodes.add(new DcsCode(72));
//        inverseCodes.add(new DcsCode(523));
//        inverseCodes.add(new DcsCode(165));
//        inverseCodes.add(new DcsCode(462));
//        inverseCodes.add(new DcsCode(446));
//        inverseCodes.add(new DcsCode(732));
//        inverseCodes.add(new DcsCode(205));
//        inverseCodes.add(new DcsCode(156));
//        inverseCodes.add(new DcsCode(454));
//        inverseCodes.add(new DcsCode(65));
//        inverseCodes.add(new DcsCode(145));
//        inverseCodes.add(new DcsCode(71));
//        inverseCodes.add(new DcsCode(664));
//        inverseCodes.add(new DcsCode(423));
//        inverseCodes.add(new DcsCode(526));
//        inverseCodes.add(new DcsCode(465));
//        inverseCodes.add(new DcsCode(455));
//        inverseCodes.add(new DcsCode(532));
//        inverseCodes.add(new DcsCode(612));
//        inverseCodes.add(new DcsCode(243));
//        inverseCodes.add(new DcsCode(212));
//        inverseCodes.add(new DcsCode(131));
//        inverseCodes.add(new DcsCode(125));
//        inverseCodes.add(new DcsCode(734));
//        inverseCodes.add(new DcsCode(226));
//        inverseCodes.add(new DcsCode(143));
//        inverseCodes.add(new DcsCode(54));
//        inverseCodes.add(new DcsCode(315));
//        inverseCodes.add(new DcsCode(723));
//        inverseCodes.add(new DcsCode(516));
//        inverseCodes.add(new DcsCode(43));
//        inverseCodes.add(new DcsCode(255));
//        inverseCodes.add(new DcsCode(53));
//        inverseCodes.add(new DcsCode(266));
//        inverseCodes.add(new DcsCode(332));
//        inverseCodes.add(new DcsCode(252));
//        inverseCodes.add(new DcsCode(26));
//        inverseCodes.add(new DcsCode(331));
//        inverseCodes.add(new DcsCode(662));
//        inverseCodes.add(new DcsCode(162));
//        inverseCodes.add(new DcsCode(73));
//        inverseCodes.add(new DcsCode(432));
//        inverseCodes.add(new DcsCode(246));
//        inverseCodes.add(new DcsCode(325));
//        inverseCodes.add(new DcsCode(343));
//        inverseCodes.add(new DcsCode(132));
//        inverseCodes.add(new DcsCode(703));
//        inverseCodes.add(new DcsCode(631));
//        inverseCodes.add(new DcsCode(346));
//        inverseCodes.add(new DcsCode(632));
//        inverseCodes.add(new DcsCode(31));
//        inverseCodes.add(new DcsCode(606));
//        inverseCodes.add(new DcsCode(624));
//        inverseCodes.add(new DcsCode(743));
//        inverseCodes.add(new DcsCode(466));
//        inverseCodes.add(new DcsCode(311));
//        inverseCodes.add(new DcsCode(565));
//        inverseCodes.add(new DcsCode(114));
//        inverseCodes.add(new DcsCode(431));
//        inverseCodes.add(new DcsCode(155));
//        inverseCodes.add(new DcsCode(261));
//        inverseCodes.add(new DcsCode(371));
//        inverseCodes.add(new DcsCode(654));
//        inverseCodes.add(new DcsCode(116));
//        sInverseCodes = Collections.unmodifiableList(inverseCodes);
//    }
//
//    @Override
//    protected List<Frequency> getKeys() {
//        return sCodes;
//    }
//
//    @Override
//    public Integer getNameStringId() {
//        return R.string.direct_dcs;
//    }
//
//    public static DcsCode getInverseCode(DcsCode directCode) {
//        final int index = sCodes.indexOf(directCode);
//        return index >= 0 ? (DcsCode) sInverseCodes.get(index) : null;
//    }
//}
