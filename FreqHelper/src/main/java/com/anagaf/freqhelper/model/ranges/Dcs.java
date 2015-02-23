package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
023	047 i
025	244 i
026	464 i
031	627 i
032	051 i
036	172 i
043	445 i
047	023 i
051	032 i
053	452 i
054	413 i
065	271 i
071	306 i
072	245 i
073	506 i
074	174 i
114	712 i
115	152 i
116	754 i
122	225 i
125	365 i
131	364 i
132	546 i
134	223 i
143	412 i
145	274 i
152	115 i
155	731 i
156	265 i
162	503 i
165	251 i
172	036 i
174	074 i
205	263 i
212	356 i
223	134 i
225	122 i
226	411 i
243	351 i
244	025 i
245	072 i
246	523 i
251	165 i
252	462 i
255	446 i
261	732 i
263	205 i
265	156 i
266	454 i
271	065 i
274	145 i
306	071 i
311	664 i
315	423 i
325	526 i
331	465 i
332	455 i
343	532 i
346	612 i
351	243 i
356	212 i
364	131 i
365	125 i
371	734 i
411	226 i
412	143 i
413	054 i
423	315 i
431	723 i
432	516 i
445	043 i
446	255 i
452	053 i
454	266 i
455	332 i
462	252 i
464	026 i
465	331 i
466	662 i
503	162 i
506	073 i
516	432 i
523	246 i
526	325 i
532	343 i
546	132 i
565	703 i
606	631 i
612	346 i
624	632 i
627	031 i
631	606 i
632	624 i
654	743 i
662	466 i
664	311 i
703	565 i
712	114 i
723	431 i
731	155 i
732	261 i
734	371 i
743	654 i
754	116 i
*/
public class Dcs extends StaticRange {

    private static final List<Long> sCodes;
    private static final List<Long> sInverseCodes;
    static {
        List<Long> codes = new ArrayList<>();
        codes.add(23L);
        codes.add(25L);
        codes.add(26L);
        codes.add(31L);
        codes.add(32L);
        codes.add(36L);
        codes.add(43L);
        codes.add(47L);
        codes.add(51L);
        codes.add(53L);
        codes.add(54L);
        codes.add(65L);
        codes.add(71L);
        codes.add(72L);
        codes.add(73L);
        codes.add(74L);
        codes.add(114L);
        codes.add(115L);
        codes.add(116L);
        codes.add(122L);
        codes.add(125L);
        codes.add(131L);
        codes.add(132L);
        codes.add(134L);
        codes.add(143L);
        codes.add(145L);
        codes.add(152L);
        codes.add(155L);
        codes.add(156L);
        codes.add(162L);
        codes.add(165L);
        codes.add(172L);
        codes.add(174L);
        codes.add(205L);
        codes.add(212L);
        codes.add(223L);
        codes.add(225L);
        codes.add(226L);
        codes.add(243L);
        codes.add(244L);
        codes.add(245L);
        codes.add(246L);
        codes.add(251L);
        codes.add(252L);
        codes.add(255L);
        codes.add(261L);
        codes.add(263L);
        codes.add(265L);
        codes.add(266L);
        codes.add(271L);
        codes.add(274L);
        codes.add(306L);
        codes.add(311L);
        codes.add(315L);
        codes.add(325L);
        codes.add(331L);
        codes.add(332L);
        codes.add(343L);
        codes.add(346L);
        codes.add(351L);
        codes.add(356L);
        codes.add(364L);
        codes.add(365L);
        codes.add(371L);
        codes.add(411L);
        codes.add(412L);
        codes.add(413L);
        codes.add(423L);
        codes.add(431L);
        codes.add(432L);
        codes.add(445L);
        codes.add(446L);
        codes.add(452L);
        codes.add(454L);
        codes.add(455L);
        codes.add(462L);
        codes.add(464L);
        codes.add(465L);
        codes.add(466L);
        codes.add(503L);
        codes.add(506L);
        codes.add(516L);
        codes.add(523L);
        codes.add(526L);
        codes.add(532L);
        codes.add(546L);
        codes.add(565L);
        codes.add(606L);
        codes.add(612L);
        codes.add(624L);
        codes.add(627L);
        codes.add(631L);
        codes.add(632L);
        codes.add(654L);
        codes.add(662L);
        codes.add(664L);
        codes.add(703L);
        codes.add(712L);
        codes.add(723L);
        codes.add(731L);
        codes.add(732L);
        codes.add(734L);
        codes.add(743L);
        codes.add(754L);
        sCodes = Collections.unmodifiableList(codes);

        List<Long> inverseCodes = new ArrayList<>();
        inverseCodes.add(47L);
        inverseCodes.add(244L);
        inverseCodes.add(464L);
        inverseCodes.add(627L);
        inverseCodes.add(51L);
        inverseCodes.add(172L);
        inverseCodes.add(445L);
        inverseCodes.add(23L);
        inverseCodes.add(32L);
        inverseCodes.add(452L);
        inverseCodes.add(413L);
        inverseCodes.add(271L);
        inverseCodes.add(306L);
        inverseCodes.add(245L);
        inverseCodes.add(506L);
        inverseCodes.add(174L);
        inverseCodes.add(712L);
        inverseCodes.add(152L);
        inverseCodes.add(754L);
        inverseCodes.add(225L);
        inverseCodes.add(365L);
        inverseCodes.add(364L);
        inverseCodes.add(546L);
        inverseCodes.add(223L);
        inverseCodes.add(412L);
        inverseCodes.add(274L);
        inverseCodes.add(115L);
        inverseCodes.add(731L);
        inverseCodes.add(265L);
        inverseCodes.add(503L);
        inverseCodes.add(251L);
        inverseCodes.add(36L);
        inverseCodes.add(74L);
        inverseCodes.add(263L);
        inverseCodes.add(356L);
        inverseCodes.add(134L);
        inverseCodes.add(122L);
        inverseCodes.add(411L);
        inverseCodes.add(351L);
        inverseCodes.add(25L);
        inverseCodes.add(72L);
        inverseCodes.add(523L);
        inverseCodes.add(165L);
        inverseCodes.add(462L);
        inverseCodes.add(446L);
        inverseCodes.add(732L);
        inverseCodes.add(205L);
        inverseCodes.add(156L);
        inverseCodes.add(454L);
        inverseCodes.add(65L);
        inverseCodes.add(145L);
        inverseCodes.add(71L);
        inverseCodes.add(664L);
        inverseCodes.add(423L);
        inverseCodes.add(526L);
        inverseCodes.add(465L);
        inverseCodes.add(455L);
        inverseCodes.add(532L);
        inverseCodes.add(612L);
        inverseCodes.add(243L);
        inverseCodes.add(212L);
        inverseCodes.add(131L);
        inverseCodes.add(125L);
        inverseCodes.add(734L);
        inverseCodes.add(226L);
        inverseCodes.add(143L);
        inverseCodes.add(54L);
        inverseCodes.add(315L);
        inverseCodes.add(723L);
        inverseCodes.add(516L);
        inverseCodes.add(43L);
        inverseCodes.add(255L);
        inverseCodes.add(53L);
        inverseCodes.add(266L);
        inverseCodes.add(332L);
        inverseCodes.add(252L);
        inverseCodes.add(26L);
        inverseCodes.add(331L);
        inverseCodes.add(662L);
        inverseCodes.add(162L);
        inverseCodes.add(73L);
        inverseCodes.add(432L);
        inverseCodes.add(246L);
        inverseCodes.add(325L);
        inverseCodes.add(343L);
        inverseCodes.add(132L);
        inverseCodes.add(703L);
        inverseCodes.add(631L);
        inverseCodes.add(346L);
        inverseCodes.add(632L);
        inverseCodes.add(31L);
        inverseCodes.add(606L);
        inverseCodes.add(624L);
        inverseCodes.add(743L);
        inverseCodes.add(466L);
        inverseCodes.add(311L);
        inverseCodes.add(565L);
        inverseCodes.add(114L);
        inverseCodes.add(431L);
        inverseCodes.add(155L);
        inverseCodes.add(261L);
        inverseCodes.add(371L);
        inverseCodes.add(654L);
        inverseCodes.add(116L);
        sInverseCodes = Collections.unmodifiableList(inverseCodes);
    }

    @Override
    protected List<Long> getValues() {
        return sCodes;
    }

    @Override
    public Integer getNameStringId() {
        return R.string.channel;
    }

    public static Long getInverseCode(Long code) {
        final int index = sCodes.indexOf(code);
        return (index >= 0 ? sInverseCodes.get(index) : Range.INVALID_VALUE);
    }
}
