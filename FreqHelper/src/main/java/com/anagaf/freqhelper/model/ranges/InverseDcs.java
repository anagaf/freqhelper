//package com.anagaf.freqhelper.model.ranges;
//
//import com.anagaf.freqhelper.R;
//import com.anagaf.freqhelper.model.keys.Frequency;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class InverseDcs extends StaticRange{
//
//    private static final List<Long> sCodes;
//    static {
//        List<Long> codes = new ArrayList<>();
//        codes.add(new DcsCode(47));
//        codes.add(new DcsCode(244));
//        codes.add(new DcsCode(464));
//        codes.add(new DcsCode(627));
//        codes.add(new DcsCode(51));
//        codes.add(new DcsCode(172));
//        codes.add(new DcsCode(445));
//        codes.add(new DcsCode(23));
//        codes.add(new DcsCode(32));
//        codes.add(new DcsCode(452));
//        codes.add(new DcsCode(413));
//        codes.add(new DcsCode(271));
//        codes.add(new DcsCode(306));
//        codes.add(new DcsCode(245));
//        codes.add(new DcsCode(506));
//        codes.add(new DcsCode(174));
//        codes.add(new DcsCode(712));
//        codes.add(new DcsCode(152));
//        codes.add(new DcsCode(754));
//        codes.add(new DcsCode(225));
//        codes.add(new DcsCode(365));
//        codes.add(new DcsCode(364));
//        codes.add(new DcsCode(546));
//        codes.add(new DcsCode(223));
//        codes.add(new DcsCode(412));
//        codes.add(new DcsCode(274));
//        codes.add(new DcsCode(115));
//        codes.add(new DcsCode(731));
//        codes.add(new DcsCode(265));
//        codes.add(new DcsCode(503));
//        codes.add(new DcsCode(251));
//        codes.add(new DcsCode(36));
//        codes.add(new DcsCode(74));
//        codes.add(new DcsCode(263));
//        codes.add(new DcsCode(356));
//        codes.add(new DcsCode(134));
//        codes.add(new DcsCode(122));
//        codes.add(new DcsCode(411));
//        codes.add(new DcsCode(351));
//        codes.add(new DcsCode(25));
//        codes.add(new DcsCode(72));
//        codes.add(new DcsCode(523));
//        codes.add(new DcsCode(165));
//        codes.add(new DcsCode(462));
//        codes.add(new DcsCode(446));
//        codes.add(new DcsCode(732));
//        codes.add(new DcsCode(205));
//        codes.add(new DcsCode(156));
//        codes.add(new DcsCode(454));
//        codes.add(new DcsCode(65));
//        codes.add(new DcsCode(145));
//        codes.add(new DcsCode(71));
//        codes.add(new DcsCode(664));
//        codes.add(new DcsCode(423));
//        codes.add(new DcsCode(526));
//        codes.add(new DcsCode(465));
//        codes.add(new DcsCode(455));
//        codes.add(new DcsCode(532));
//        codes.add(new DcsCode(612));
//        codes.add(new DcsCode(243));
//        codes.add(new DcsCode(212));
//        codes.add(new DcsCode(131));
//        codes.add(new DcsCode(125));
//        codes.add(new DcsCode(734));
//        codes.add(new DcsCode(226));
//        codes.add(new DcsCode(143));
//        codes.add(new DcsCode(54));
//        codes.add(new DcsCode(315));
//        codes.add(new DcsCode(723));
//        codes.add(new DcsCode(516));
//        codes.add(new DcsCode(43));
//        codes.add(new DcsCode(255));
//        codes.add(new DcsCode(53));
//        codes.add(new DcsCode(266));
//        codes.add(new DcsCode(332));
//        codes.add(new DcsCode(252));
//        codes.add(new DcsCode(26));
//        codes.add(new DcsCode(331));
//        codes.add(new DcsCode(662));
//        codes.add(new DcsCode(162));
//        codes.add(new DcsCode(73));
//        codes.add(new DcsCode(432));
//        codes.add(new DcsCode(246));
//        codes.add(new DcsCode(325));
//        codes.add(new DcsCode(343));
//        codes.add(new DcsCode(132));
//        codes.add(new DcsCode(703));
//        codes.add(new DcsCode(631));
//        codes.add(new DcsCode(346));
//        codes.add(new DcsCode(632));
//        codes.add(new DcsCode(31));
//        codes.add(new DcsCode(606));
//        codes.add(new DcsCode(624));
//        codes.add(new DcsCode(743));
//        codes.add(new DcsCode(466));
//        codes.add(new DcsCode(311));
//        codes.add(new DcsCode(565));
//        codes.add(new DcsCode(114));
//        codes.add(new DcsCode(431));
//        codes.add(new DcsCode(155));
//        codes.add(new DcsCode(261));
//        codes.add(new DcsCode(371));
//        codes.add(new DcsCode(654));
//        codes.add(new DcsCode(116));
//        sCodes = Collections.unmodifiableList(codes);
//    }
//
//    @Override
//    protected List<Frequency> getKeys() {
//        return sCodes;
//    }
//
//    @Override
//    public Integer getNameStringId() {
//        return R.string.inverse_dcs;
//    }
//}
